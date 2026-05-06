import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'controllers/settings_controller.dart';
import 'l10n/generated/app_localizations.dart';
import 'pages/main_page.dart';
import 'services/app_cache_service.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  AppCacheService.instance.initialize();
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final _ctrl = SettingsController.instance;
  late ThemeMode _themeMode;
  Locale? _locale;
  int _seedColor = 0xFF6750A4;
  bool _blurBars = false;

  @override
  void initState() {
    super.initState();
    _themeMode = _ctrl.themeMode;
    _locale = _ctrl.locale;
    _seedColor = _ctrl.themeSeedColor;
    _blurBars = _ctrl.blurBars;
    _ctrl.addListener(_onSettingsChanged);
  }

  @override
  void dispose() {
    _ctrl.removeListener(_onSettingsChanged);
    super.dispose();
  }

  void _onSettingsChanged() {
    final nextThemeMode = _ctrl.themeMode;
    final nextLocale = _ctrl.locale;
    final nextSeedColor = _ctrl.themeSeedColor;
    final nextBlurBars = _ctrl.blurBars;
    if (nextThemeMode == _themeMode &&
        nextLocale == _locale &&
        nextSeedColor == _seedColor &&
        nextBlurBars == _blurBars) {
      return;
    }
    if (!mounted) return;
    setState(() {
      _themeMode = nextThemeMode;
      _locale = nextLocale;
      _seedColor = nextSeedColor;
      _blurBars = nextBlurBars;
    });
  }

  ThemeData _buildTheme({
    required Color seedColor,
    required Brightness brightness,
    required bool blur,
  }) {
    final colorScheme = ColorScheme.fromSeed(
      seedColor: seedColor,
      brightness: brightness,
    );
    return ThemeData(
      colorScheme: colorScheme,
      useMaterial3: true,
      appBarTheme: AppBarTheme(
        backgroundColor: blur
            ? Colors.transparent
            : colorScheme.surface,
        scrolledUnderElevation: blur ? 0 : null,
        systemOverlayStyle: blur
            ? (brightness == Brightness.light
                ? SystemUiOverlayStyle.dark
                : SystemUiOverlayStyle.light)
            : null,
      ),
      navigationBarTheme: NavigationBarThemeData(
        backgroundColor: blur
            ? colorScheme.surface.withValues(alpha: 0.7)
            : colorScheme.surface,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final seedColor = Color(_seedColor);

    return MaterialApp(
      title: 'HyperIsland',
      localizationsDelegates: const [
        AppLocalizations.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: AppLocalizations.supportedLocales,
      locale: _locale,
      theme: _buildTheme(
        seedColor: seedColor,
        brightness: Brightness.light,
        blur: _blurBars,
      ),
      darkTheme: _buildTheme(
        seedColor: seedColor,
        brightness: Brightness.dark,
        blur: _blurBars,
      ),
      themeMode: _themeMode,
      home: const MainPage(),
    );
  }
}
