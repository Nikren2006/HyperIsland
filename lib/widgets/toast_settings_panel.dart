import 'package:flutter/material.dart';

import '../controllers/whitelist_controller.dart';
import '../l10n/generated/app_localizations.dart';

class ToastSettingsPanel extends StatelessWidget {
  const ToastSettingsPanel({
    super.key,
    required this.forwardEnabled,
    required this.blockOriginal,
    required this.showNotification,
    this.onForwardEnabledChanged,
    this.onBlockOriginalChanged,
    this.onShowNotificationChanged,
    this.showHint = true,
    this.allowIndependentBlockOriginal = false,
  });

  final bool forwardEnabled;
  final bool blockOriginal;
  final bool showNotification;
  final ValueChanged<bool>? onForwardEnabledChanged;
  final ValueChanged<bool>? onBlockOriginalChanged;
  final ValueChanged<bool>? onShowNotificationChanged;
  final bool showHint;
  final bool allowIndependentBlockOriginal;

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;
    final cs = Theme.of(context).colorScheme;

    return Container(
      decoration: BoxDecoration(
        color: cs.surfaceContainerHighest,
        borderRadius: BorderRadius.circular(16),
      ),
      child: Column(
        children: [
          SwitchListTile(
            value: forwardEnabled,
            onChanged: onForwardEnabledChanged,
            title: Text(l10n.toastForwardTitle),
            subtitle: Text(l10n.toastForwardSubtitle),
          ),
          _Divider(cs: cs),
          SwitchListTile(
            value: blockOriginal,
            onChanged: allowIndependentBlockOriginal
                ? onBlockOriginalChanged
                : (forwardEnabled ? onBlockOriginalChanged : null),
            title: Text(l10n.toastBlockOriginalTitle),
            subtitle: Text(l10n.toastBlockOriginalSubtitle),
          ),
          _Divider(cs: cs),
          SwitchListTile(
            value: showNotification,
            onChanged: forwardEnabled ? onShowNotificationChanged : null,
            title: Text(l10n.toastShowNotificationTitle),
            subtitle: Text(l10n.toastShowNotificationSubtitle),
          ),
          if (showHint)
            Padding(
              padding: const EdgeInsets.fromLTRB(16, 0, 16, 12),
              child: Align(
                alignment: Alignment.centerLeft,
                child: Text(
                  l10n.toastStandardOnlyHint,
                  style: Theme.of(
                    context,
                  ).textTheme.bodySmall?.copyWith(color: cs.onSurfaceVariant),
                ),
              ),
            ),
        ],
      ),
    );
  }
}

class ToastSettingsSection extends StatelessWidget {
  const ToastSettingsSection({
    super.key,
    required this.title,
    required this.children,
    this.icon,
  });

  final String title;
  final IconData? icon;
  final List<Widget> children;

  @override
  Widget build(BuildContext context) {
    final cs = Theme.of(context).colorScheme;
    final text = Theme.of(context).textTheme;
    return Container(
      padding: const EdgeInsets.fromLTRB(16, 14, 16, 16),
      decoration: BoxDecoration(
        color: cs.surfaceContainerHighest,
        borderRadius: BorderRadius.circular(16),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              if (icon != null) ...[
                Icon(icon, color: cs.primary, size: 20),
                const SizedBox(width: 10),
              ],
              Expanded(
                child: Text(
                  title,
                  style: text.titleSmall?.copyWith(fontWeight: FontWeight.w600),
                ),
              ),
            ],
          ),
          const SizedBox(height: 12),
          ...children,
        ],
      ),
    );
  }
}

class ToastSettingField extends StatelessWidget {
  const ToastSettingField({
    super.key,
    required this.label,
    required this.child,
  });

  final String label;
  final Widget child;

  @override
  Widget build(BuildContext context) {
    final cs = Theme.of(context).colorScheme;
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          label,
          style: Theme.of(
            context,
          ).textTheme.bodyMedium?.copyWith(color: cs.onSurfaceVariant),
        ),
        const SizedBox(height: 6),
        child,
      ],
    );
  }
}

class ToastTriOptDropdown extends StatelessWidget {
  const ToastTriOptDropdown({
    super.key,
    required this.label,
    required this.value,
    required this.defaultLabel,
    required this.onChanged,
    this.enabled = true,
    this.showNotChange = false,
    this.subtitle,
    this.includeFollowDynamic = false,
  });

  final String label;
  final String? subtitle;
  final String? value;
  final String defaultLabel;
  final bool enabled;
  final bool showNotChange;
  final bool includeFollowDynamic;
  final ValueChanged<String?> onChanged;

  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;
    return ToastSettingField(
      label: subtitle == null ? label : '$label\n$subtitle',
      child: DropdownButtonFormField<String?>(
        key: ValueKey(value),
        initialValue: value,
        isExpanded: true,
        decoration: toastFieldDecoration(context),
        items: [
          if (showNotChange)
            DropdownMenuItem<String?>(value: null, child: Text(l10n.noChange)),
          DropdownMenuItem<String?>(
            value: kTriOptDefault,
            child: Text(defaultLabel),
          ),
          DropdownMenuItem<String?>(value: kTriOptOn, child: Text(l10n.optOn)),
          DropdownMenuItem<String?>(
            value: kTriOptOff,
            child: Text(l10n.optOff),
          ),
          if (includeFollowDynamic)
            DropdownMenuItem<String?>(
              value: kTriOptFollowDynamic,
              child: Text(l10n.followDynamicColorLabel),
            ),
        ],
        onChanged: enabled ? onChanged : null,
      ),
    );
  }
}

class ToastKeywordListEditor extends StatefulWidget {
  const ToastKeywordListEditor({
    super.key,
    required this.label,
    required this.keywords,
    required this.enabled,
    required this.onAdd,
    required this.onRemove,
    required this.hintText,
  });

  final String label;
  final List<String> keywords;
  final bool enabled;
  final ValueChanged<String> onAdd;
  final ValueChanged<String> onRemove;
  final String hintText;

  @override
  State<ToastKeywordListEditor> createState() => _ToastKeywordListEditorState();
}

class _ToastKeywordListEditorState extends State<ToastKeywordListEditor> {
  late final TextEditingController _controller;

  @override
  void initState() {
    super.initState();
    _controller = TextEditingController();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  void _addKeyword() {
    if (!widget.enabled) return;
    final keyword = _controller.text.trim();
    if (keyword.isEmpty || widget.keywords.contains(keyword)) return;
    widget.onAdd(keyword);
    _controller.clear();
  }

  @override
  Widget build(BuildContext context) {
    final cs = Theme.of(context).colorScheme;
    return ToastSettingField(
      label: widget.label,
      child: Opacity(
        opacity: widget.enabled ? 1 : 0.45,
        child: Column(
          children: [
            Row(
              children: [
                Expanded(
                  child: TextField(
                    controller: _controller,
                    enabled: widget.enabled,
                    decoration: toastFieldDecoration(
                      context,
                      hintText: widget.hintText,
                    ),
                    textInputAction: TextInputAction.done,
                    onSubmitted: (_) => _addKeyword(),
                  ),
                ),
                const SizedBox(width: 8),
                IconButton(
                  onPressed: widget.enabled ? _addKeyword : null,
                  icon: const Icon(Icons.add_rounded),
                  style: IconButton.styleFrom(
                    backgroundColor: cs.primaryContainer,
                    foregroundColor: cs.onPrimaryContainer,
                  ),
                ),
              ],
            ),
            if (widget.keywords.isNotEmpty) ...[
              const SizedBox(height: 8),
              Align(
                alignment: Alignment.centerLeft,
                child: Wrap(
                  spacing: 6,
                  runSpacing: 6,
                  children: widget.keywords
                      .map(
                        (kw) => InputChip(
                          label: Text(kw),
                          onDeleted: widget.enabled
                              ? () => widget.onRemove(kw)
                              : null,
                          deleteIconColor: cs.error,
                          materialTapTargetSize:
                              MaterialTapTargetSize.shrinkWrap,
                        ),
                      )
                      .toList(),
                ),
              ),
            ],
          ],
        ),
      ),
    );
  }
}

InputDecoration toastFieldDecoration(
  BuildContext context, {
  String? hintText,
  String? suffixText,
}) {
  final cs = Theme.of(context).colorScheme;
  final border = OutlineInputBorder(
    borderRadius: BorderRadius.circular(12),
    borderSide: BorderSide(color: cs.outlineVariant),
  );
  return InputDecoration(
    hintText: hintText,
    suffixText: suffixText,
    isDense: true,
    filled: true,
    fillColor: cs.surfaceContainerHigh,
    contentPadding: const EdgeInsets.symmetric(horizontal: 14, vertical: 10),
    border: border,
    enabledBorder: border,
    focusedBorder: border.copyWith(borderSide: BorderSide(color: cs.primary)),
  );
}

class _Divider extends StatelessWidget {
  const _Divider({required this.cs});

  final ColorScheme cs;

  @override
  Widget build(BuildContext context) {
    return Divider(
      height: 1,
      thickness: 1,
      indent: 12,
      endIndent: 12,
      color: cs.outlineVariant.withValues(alpha: 0.4),
    );
  }
}
