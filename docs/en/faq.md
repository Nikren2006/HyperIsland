# FAQ

::: details No effect after installation?
Please confirm:
1. Module is enabled in LSPosed with correct scope settings
2. Scope has been restarted
3. Focus Notification whitelist bypass is enabled in HyperCeiler
4. System is HyperOS 3 with LSPosed API 101
5. Tap the test button on the app's main page — if no notification appears, LSPosed module is not working; if a standard Android notification appears, Focus Notification bypass is not working
:::

::: details Notifications not showing in Super Island style?
- Make sure the app sends **standard Android notifications** — custom notification styles are not supported
- Check if the app has Focus Notification permission in system notification settings
- Confirm HyperCeiler Focus Notification bypass is correctly configured
:::

::: details Notification style changed. How do I restore it?
- Disable Focus Notification in the app settings
:::

::: details Super Island lighting effects not working?
1. Check whether the system is HyperOS 3 based on Android 16
2. Check whether you are using third-party System UI components
3. Third-party systems may not be supported
:::

::: details Configuration changes not taking effect?
- Scope needs to be restarted after app updates
- Try restarting System UI
:::

::: details What if both floating notifications and focus notifications appear simultaneously?
- Please try disabling floating notifications in the system's native notification settings.
:::

::: danger Found a BUG?
Please upload LSPosed logs along with reproduction steps or a complete video. Though realistically, there's a good chance it won't get fixed ):
:::
