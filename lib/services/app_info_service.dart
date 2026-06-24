import 'package:flutter/services.dart';

class AppInfoService {
  static const _channel = MethodChannel('io.github.hyperisland/test');

  static Future<String> getVersion() async {
    final version = await _channel.invokeMethod<String>('getAppVersion');
    return version ?? '';
  }

  static Future<String> getBuildTime() async {
    final buildTime = await _channel.invokeMethod<String>('getBuildTime');
    return buildTime ?? '';
  }
}
