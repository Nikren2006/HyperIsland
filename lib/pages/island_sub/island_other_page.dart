import 'package:flutter/material.dart';
import '../../controllers/settings_controller.dart';
import '../../l10n/generated/app_localizations.dart';
import '../../services/interaction_haptics.dart';
import '../../widgets/blur_app_bar.dart';
import '../../widgets/color_picker_dialog.dart';
import '../../widgets/modern_slider.dart';

class IslandOtherPage extends StatefulWidget {
  const IslandOtherPage({super.key});

  @override
  State<IslandOtherPage> createState() => _IslandOtherPageState();
}

class _IslandOtherPageState extends State<IslandOtherPage> {
  final _ctrl = SettingsController.instance;
  late int _marqueeSpeedDraft;
  late int _buildHash;

  int _computeHash() => Object.hashAll([
        _ctrl.fullscreenBehavior,
        _ctrl.marqueeSpeed,
        _ctrl.keepIsland,
        _ctrl.keepIslandAutoHide,
        _ctrl.keepIslandHighlightColor,
      ]);

  @override
  void initState() {
    super.initState();
    _marqueeSpeedDraft = _ctrl.marqueeSpeed;
    _buildHash = _computeHash();
    _ctrl.addListener(_onChanged);
    // 消息滚动不再有开关，确保 feature 始终启用
    if (!_ctrl.marqueeFeature) {
      _ctrl.setMarqueeFeature(true);
    }
  }

  @override
  void dispose() {
    _ctrl.removeListener(_onChanged);
    super.dispose();
  }

  void _onChanged() {
    if (!mounted) return;
    final nextHash = _computeHash();
    final nextSpeed = _ctrl.marqueeSpeed;
    if (nextHash == _buildHash && nextSpeed == _marqueeSpeedDraft) return;
    setState(() {
      _buildHash = nextHash;
      _marqueeSpeedDraft = nextSpeed;
    });
  }

  String _fullscreenBehaviorLabel(AppLocalizations l10n, String value) {
    return switch (value) {
      'fallback' => l10n.fullscreenBehaviorFallback,
      'expand' => l10n.fullscreenBehaviorExpand,
      _ => l10n.fullscreenBehaviorOff,
    };
  }

  @override
  Widget build(BuildContext context) {
    final cs = Theme.of(context).colorScheme;
    final l10n = AppLocalizations.of(context)!;
    final titleStyle = Theme.of(context).textTheme.titleMedium;

    return Scaffold(
      backgroundColor: cs.surface,
      body: BlurAppBarHost(
        title: l10n.islandOtherSection,
        physics: const ClampingScrollPhysics(),
        slivers: [
          SliverPadding(
            padding: const EdgeInsets.symmetric(horizontal: 16),
            sliver: SliverList(
              delegate: SliverChildListDelegate(
                [
                  const SizedBox(height: 8),
                  // --- 全屏时行为 ---
                  _SectionLabel(l10n.fullscreenBehaviorTitle),
                  const SizedBox(height: 8),
                  Card(
                    elevation: 0,
                    color: cs.surfaceContainerHighest,
                    child: Column(
                      children: [
                        ListTile(
                          contentPadding: const EdgeInsets.symmetric(
                            horizontal: 16,
                            vertical: 4,
                          ),
                          title: Text(
                            l10n.fullscreenBehaviorTitle,
                            style: titleStyle,
                          ),
                          subtitle: Text(
                            l10n.fullscreenBehaviorSubtitle,
                            style: Theme.of(context).textTheme.bodySmall
                                ?.copyWith(color: cs.onSurfaceVariant),
                          ),
                          trailing: DropdownButtonHideUnderline(
                            child: DropdownButton<String>(
                              value: _ctrl.fullscreenBehavior,
                              onChanged: InteractionHaptics.interceptDropdown(
                                (v) async {
                                  if (v == null) return;
                                  await _ctrl.setFullscreenBehavior(v);
                                },
                              ),
                              items: [
                                DropdownMenuItem<String>(
                                  value: 'off',
                                  child: Text(
                                      _fullscreenBehaviorLabel(l10n, 'off')),
                                ),
                                DropdownMenuItem<String>(
                                  value: 'fallback',
                                  child: Text(_fullscreenBehaviorLabel(
                                      l10n, 'fallback')),
                                ),
                                DropdownMenuItem<String>(
                                  value: 'expand',
                                  child: Text(_fullscreenBehaviorLabel(
                                      l10n, 'expand')),
                                ),
                              ],
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 8),
                  // --- 消息滚动（速度滑块，无开关） ---
                  _SectionLabel(l10n.marqueeChannelTitle),
                  const SizedBox(height: 8),
                  Card(
                    elevation: 0,
                    color: cs.surfaceContainerHighest,
                    child: ListTile(
                      contentPadding: const EdgeInsets.symmetric(
                        horizontal: 16,
                        vertical: 2,
                      ),
                      title: Row(
                        children: [
                          Expanded(
                            child: Text(
                              l10n.marqueeChannelTitle,
                              style: titleStyle,
                            ),
                          ),
                          Text(
                            l10n.marqueeSpeedLabel(_marqueeSpeedDraft),
                            style: Theme.of(context).textTheme.bodySmall
                                ?.copyWith(color: cs.onSurfaceVariant),
                          ),
                          if (_marqueeSpeedDraft != 100)
                            SizedBox(
                              width: 18,
                              height: 18,
                              child: IconButton(
                                icon: const Icon(Icons.refresh, size: 18),
                                padding: EdgeInsets.zero,
                                visualDensity: VisualDensity.compact,
                                onPressed: InteractionHaptics.interceptButton(
                                  () {
                                    setState(() => _marqueeSpeedDraft = 100);
                                    _ctrl.setMarqueeSpeed(100);
                                  },
                                ),
                              ),
                            ),
                        ],
                      ),
                      subtitle: Row(
                        children: [
                          Text(
                            l10n.marqueeSpeedTitle,
                            style: Theme.of(context).textTheme.bodySmall
                                ?.copyWith(color: cs.onSurfaceVariant),
                          ),
                          Expanded(
                            child: SliderTheme(
                              data: ModernSliderTheme.theme(context),
                              child: Slider(
                                value: _marqueeSpeedDraft.toDouble(),
                                min: 20,
                                max: 500,
                                divisions: 48,
                                onChanged: InteractionHaptics.interceptSlider(
                                  (v) {
                                    final next = v.round();
                                    if (_marqueeSpeedDraft == next) return;
                                    setState(() => _marqueeSpeedDraft = next);
                                  },
                                ),
                                onChangeEnd: (v) async {
                                  final next = v.round();
                                  if (_ctrl.marqueeSpeed == next) return;
                                  await _ctrl.setMarqueeSpeed(next);
                                },
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                  const SizedBox(height: 8),
                  // --- 常驻岛 ---
                  _SectionLabel(l10n.keepIslandTitle),
                  const SizedBox(height: 8),
                  Card(
                    elevation: 0,
                    color: cs.surfaceContainerHighest,
                    child: Column(
                      children: [
                        SwitchListTile(
                          contentPadding: const EdgeInsets.symmetric(
                            horizontal: 16,
                            vertical: 4,
                          ),
                          title: Text(l10n.keepIslandTitle, style: titleStyle),
                          subtitle: Text(l10n.keepIslandSubtitle),
                          value: _ctrl.keepIsland,
                          onChanged: InteractionHaptics.interceptToggle(
                            (v) => _ctrl.setKeepIsland(v),
                          ),
                          shape: RoundedRectangleBorder(
                            borderRadius: _ctrl.keepIsland
                                ? const BorderRadius.vertical(
                                    top: Radius.circular(16),
                                  )
                                : BorderRadius.circular(16),
                          ),
                        ),
                        if (_ctrl.keepIsland) ...[
                          const Divider(height: 1, indent: 16, endIndent: 16),
                          SwitchListTile(
                            contentPadding: const EdgeInsets.symmetric(
                              horizontal: 16,
                              vertical: 4,
                            ),
                            title: Text(l10n.keepIslandAutoHideTitle,
                                style: titleStyle),
                            subtitle: Text(l10n.keepIslandAutoHideSubtitle),
                            value: _ctrl.keepIslandAutoHide,
                            onChanged: InteractionHaptics.interceptToggle(
                              (v) => _ctrl.setKeepIslandAutoHide(v),
                            ),
                          ),
                          const Divider(height: 1, indent: 16, endIndent: 16),
                          ListTile(
                            contentPadding: const EdgeInsets.symmetric(
                              horizontal: 16,
                              vertical: 4,
                            ),
                            title: Text(l10n.keepIslandHighlightColorTitle,
                                style: titleStyle),
                            subtitle: Text(l10n.keepIslandHighlightColorSubtitle),
                            trailing: Row(
                              mainAxisSize: MainAxisSize.min,
                              children: [
                                if (_ctrl.keepIslandHighlightColor.isNotEmpty)
                                  Container(
                                    width: 24,
                                    height: 24,
                                    decoration: BoxDecoration(
                                      color: parseHexColor(
                                              _ctrl.keepIslandHighlightColor) ??
                                          cs.primary,
                                      borderRadius: BorderRadius.circular(6),
                                      border: Border.all(
                                          color: cs.outline, width: 1),
                                    ),
                                  )
                                else
                                  Icon(Icons.palette_outlined,
                                      color: cs.onSurfaceVariant),
                                const SizedBox(width: 8),
                                if (_ctrl.keepIslandHighlightColor.isNotEmpty)
                                  SizedBox(
                                    width: 18,
                                    height: 18,
                                    child: IconButton(
                                      icon: const Icon(Icons.refresh, size: 18),
                                      padding: EdgeInsets.zero,
                                      visualDensity: VisualDensity.compact,
                                      onPressed: InteractionHaptics
                                          .interceptButton(
                                        () => _ctrl
                                            .setKeepIslandHighlightColor(''),
                                      ),
                                    ),
                                  ),
                              ],
                            ),
                            onTap: InteractionHaptics.interceptButton(
                              () async {
                                final color = await showColorPickerDialog(
                                  context,
                                  initialHex:
                                      _ctrl.keepIslandHighlightColor.isEmpty
                                          ? null
                                          : _ctrl.keepIslandHighlightColor,
                                  title: l10n.keepIslandHighlightColorTitle,
                                  enableAlpha: false,
                                );
                                if (color != null) {
                                  await _ctrl.setKeepIslandHighlightColor(
                                      colorToHex(color));
                                }
                              },
                            ),
                            shape: const RoundedRectangleBorder(
                              borderRadius: BorderRadius.vertical(
                                bottom: Radius.circular(16),
                              ),
                            ),
                          ),
                        ],
                      ],
                    ),
                  ),
                  const SizedBox(height: 32),
                ],
                addAutomaticKeepAlives: false,
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class _SectionLabel extends StatelessWidget {
  final String text;
  const _SectionLabel(this.text);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 18),
      child: Text(
        text,
        style: Theme.of(context).textTheme.titleSmall?.copyWith(
              color: Theme.of(context).colorScheme.primary,
              fontWeight: FontWeight.bold,
              letterSpacing: 0.5,
            ),
      ),
    );
  }
}
