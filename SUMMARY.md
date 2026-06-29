# 开发总结

## 功能概览

“滚动后隐藏岛”能力：当超级岛消息滚动达到指定次数后，主动隐藏当前岛。

支持两层配置：

- 默认配置：作为渠道选择“默认”时的 fallback。
- 渠道配置：可单独覆盖默认配置。

可选值：

- `off`：关闭。
- `1`：滚动 1 次后隐藏岛。
- `2`：滚动 2 次后隐藏岛。
- `default`：仅渠道级使用，表示跟随默认配置。

## 核心 Hook

### `MarqueeHook.kt`

位置：`android/app/src/main/kotlin/io/github/hyperisland/xposed/hook/SystemUI/MarqueeHook.kt`

职责：

- 提供超级岛消息滚动能力。
- 读取渠道/默认的“滚动后隐藏岛”配置。
- 统计消息滚动完成次数。
- 达到配置次数后调用主动隐藏岛能力。

读取逻辑：

- 渠道级 key：`pref_channel_marquee_auto_hide_${pkg}_${channelId}`
- Toast key：`pref_toast_marquee_auto_hide_${pkg}`
- 默认 key：`pref_default_marquee_auto_hide`
- 渠道值或 Toast 值为 `default` 时读取默认 key。
- 常驻通知不启用消息滚动，因此也不会触发滚动后隐藏。

### `ToastUiInterceptHook.kt`

位置：`android/app/src/main/kotlin/io/github/hyperisland/xposed/hook/SystemUI/ToastUiInterceptHook.kt`

职责：

- 拦截标准文本 Toast，并按应用配置转发到超级岛。
- 支持 Toast 的岛设置：图标、初次展开、更新展开、消息滚动、滚动后隐藏、自动消失、高亮色、外发光。
- 支持 Toast 过滤规则：黑名单命中时不弹岛且不显示原 Toast；白名单模式下未命中白名单也不显示。
- 空过滤规则不生效，避免未配置时误拦截。

### `NotificationHook.kt`

位置：`android/app/src/main/kotlin/io/github/hyperisland/xposed/hook/SystemUI/NotificationHook.kt`

职责：

- 通用通知进入模板前写入超级岛 extras。
- 对 HyperIsland Dispatcher 自有代理通知放行，避免 Toast 已发出代理通知但被 `com.android.systemui/hyperisland_dispatcher` 判断提前跳过。
- Dispatcher 代理通知使用 `hyperisland_source_pkg` 和 `hyperisland_source_channel` 作为真实来源，避免被当成 SystemUI 普通通知处理。

### `ActiveIslandDismissHook.kt`

位置：`android/app/src/main/kotlin/io/github/hyperisland/xposed/hook/SystemUI/ActiveIslandDismissHook.kt`

职责：

- 封装主动隐藏当前岛的能力。
- hook `miui.systemui.dynamicisland.window.DynamicIslandWindowViewController.updateDynamicIslandView`。
- 记录 island key 与 controller。
- 主动隐藏时调用 SystemUI 原生入口：`removeDynamicIslandView(key)`。

依据：

- JADX 确认 `IslandTemplate.dismissIsland=true` 最终在 `DynamicIslandWindowViewController.updateDynamicIslandView` 中调用 `removeDynamicIslandView(key)`。
- 因此该 hook 直接复用同一路径主动隐藏当前岛。

安全性：

- controller 使用弱引用缓存。
- key 缓存有清理和数量上限，避免长期堆积。

## Xposed 初始化与同步

### `HyperIslandModule.kt`

位置：`android/app/src/main/kotlin/io/github/hyperisland/xposed/HyperIslandModule.kt`

变更：

- SystemUI 加载时初始化 `ActiveIslandDismissHook`。
- 初始化顺序放在 `MarqueeHook` 前。

### `ConfigManager.kt`

位置：`android/app/src/main/kotlin/io/github/hyperisland/xposed/ConfigManager.kt`

新增默认配置白名单：

- `pref_default_marquee_auto_hide`

### `XposedPrefsSyncApp.kt`

位置：`android/app/src/main/kotlin/io/github/hyperisland/XposedPrefsSyncApp.kt`

新增同步配置：

- `pref_default_marquee_auto_hide`

## Flutter 配置读写

### 默认配置

页面：`lib/pages/island_sub/default_config_page.dart`

新增内容：

- 在“消息滚动”下面添加“滚动后隐藏岛”下拉选择框。
- 选项：`关`、`滚动1次`、`滚动2次`。
- 英文界面显示：`Off`、`Scroll once`、`Scroll twice`。
- 下拉外观复用现有设置页圆角 surface 样式，宽度较普通下拉更窄。

控制器：`lib/controllers/settings_controller.dart`

新增字段：

- `defaultMarqueeAutoHide`

新增 key：

- `kPrefDefaultMarqueeAutoHide = 'pref_default_marquee_auto_hide'`

新增写入方法：

- `setDefaultMarqueeAutoHide(String value)`

默认值：

- `off`

### 渠道配置

页面：`lib/widgets/batch_channel_settings_sheet.dart`

新增内容：

- 在“消息滚动”下面添加“滚动后隐藏岛”下拉选择框。
- 单渠道模式显示：`默认`、`关`、`滚动1次`、`滚动2次`。
- 批量模式支持“不更改”。
- `默认` 会显示当前默认配置，例如：`默认（关）`、`默认（滚动1次）`。

数据流：

- `SingleChannelMode` 新增 `marqueeAutoHide`。
- 提交结果新增 settings key：`marquee_auto_hide`。

控制器：`lib/controllers/whitelist_controller.dart`

新增读取：

- `getChannelExtraSettings` 返回 `marquee_auto_hide`。
- 默认值为 `default`。

新增写入：

- `setChannelMarqueeAutoHide(String packageName, String channelId, String value)`

实际 SharedPreferences key：

- `pref_channel_marquee_auto_hide_${packageName}_$channelId`

### Toast 配置

页面：`lib/pages/toast_app_settings_page.dart`

内容：

- Toast 应用设置页采用与通知渠道设置一致的分组式岛设置样式。
- 基础设置保留转发、阻止原 Toast、显示为通知。
- “显示超级岛图标”放入“岛”设置分组，不再显示在顶部基础设置中。
- “岛”设置包含：大岛图标、初次展开、更新展开、消息滚动、滚动后隐藏岛、自动消失。
- 不包含超级岛高级自定义、焦点通知高级自定义、锁屏通知复原、模板和样式、息屏显示。
- 保留高亮色、动态高亮、文字高亮、外发光、岛外发光。
- 支持过滤规则：黑名单、白名单、关键词列表。

复用组件：`lib/widgets/toast_settings_panel.dart`

内容：

- `ToastSettingsPanel` 只负责 Toast 基础开关。
- `ToastSettingsSection`、`ToastSettingField`、`ToastTriOptDropdown`、`ToastKeywordListEditor` 作为 Toast 单应用页和批量页复用的设置组件。

控制器：`lib/controllers/whitelist_controller.dart`

Toast 配置 key：

- `pref_toast_forward_${packageName}`
- `pref_toast_block_${packageName}`
- `pref_toast_show_notification_${packageName}`
- `pref_toast_show_island_icon_${packageName}`
- `pref_toast_first_float_${packageName}`
- `pref_toast_enable_float_${packageName}`
- `pref_toast_marquee_${packageName}`
- `pref_toast_marquee_auto_hide_${packageName}`
- `pref_toast_timeout_${packageName}`
- `pref_toast_highlight_color_${packageName}`
- `pref_toast_dynamic_highlight_color_${packageName}`
- `pref_toast_show_left_highlight_${packageName}`
- `pref_toast_show_right_highlight_${packageName}`
- `pref_toast_outer_glow_${packageName}`
- `pref_toast_out_effect_color_${packageName}`
- `pref_toast_island_outer_glow_${packageName}`
- `pref_toast_island_outer_glow_color_${packageName}`
- `pref_toast_filter_mode_${packageName}`
- `pref_toast_filter_whitelist_keywords_${packageName}`
- `pref_toast_filter_blacklist_keywords_${packageName}`

批量应用：

- Toast 批量设置支持岛设置、滚动后隐藏岛和过滤规则。
- 关键词以换行保存；Xposed 侧兼容换行和逗号分隔。

应用页：`lib/pages/app_channels_page.dart`

变更：

- 读取渠道 extras 中的 `marquee_auto_hide`。
- 打开单渠道设置时传入 `marqueeAutoHide`。
- 保存时调用 `setChannelMarqueeAutoHide`。

批量应用：

- `WhitelistController.batchApplyChannelSettings` 的 keyMap 新增：
- `marquee_auto_hide -> pref_channel_marquee_auto_hide`

## 配置导入导出

文件：`lib/controllers/config_io_controller.dart`

新增渠道配置前缀：

- `pref_channel_marquee_auto_hide_`

Toast 配置前缀：

- `pref_toast_marquee_auto_hide_`
- `pref_toast_filter_mode_`
- `pref_toast_filter_whitelist_keywords_`
- `pref_toast_filter_blacklist_keywords_`

用于配置导入/导出时包含渠道级“滚动后隐藏岛”设置。


注意：

- 因 generated 文件未更新，当前 Dart 页面用 `Locale` 做中英文显示。
- 后续如果运行 l10n 生成，可以改为直接使用 `AppLocalizations` getter。

## 新增类似配置项的步骤

1. 在 `settings_controller.dart` 增加默认配置 key、字段、读取逻辑和 setter。
2. 在默认配置页添加 UI。
3. 如果 Xposed 侧需要读取默认配置，在 `ConfigManager.kt` 和 `XposedPrefsSyncApp.kt` 加入同步白名单。
4. 在 `whitelist_controller.dart` 增加渠道级读取和写入方法。
5. 在渠道设置弹窗中增加字段、UI 和提交 settings key。
6. 在 `app_channels_page.dart` 接入读取、传参和保存队列。
7. 如需批量应用，在 `batchApplyChannelSettings` 的 keyMap 增加映射。
8. 如需导入导出，在 `config_io_controller.dart` 增加对应前缀。
9. 在相关 hook 中读取配置并实现行为。
10. 补充 `app_zh.arb` 和 `app_en.arb` 文案；如允许，再重新生成 l10n。

## 开发概要

- 最佳实现，确保代码规范，确保没有性能，内存泄漏等问题
- 先固定中文实现，确保功能实现后再进行国际化
- 国际化语言只要改arb文件和页面dart文件，自动生成文件不要改动
- 如果要改动该md总结文档，请勿写入新增xx，而是直接在对应位置添加总结，不要过多技术细节
