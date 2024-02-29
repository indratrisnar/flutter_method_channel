import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  static const myChannel = MethodChannel('flutterdelux.com/toast');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          ElevatedButton(
            onPressed: _showToast,
            child: const Text('Show Toast'),
          ),
          const SizedBox(height: 16),
          ElevatedButton(
            onPressed: _showToastArgument,
            child: const Text('Show Toast with Argument'),
          ),
          const SizedBox(height: 16),
          ElevatedButton(
            onPressed: _accessData,
            child: const Text('Access Data From Android'),
          ),
        ],
      ),
    );
  }

  _showToast() {
    myChannel.invokeMethod('showShortToast');
  }

  _showToastArgument() async {
    try {
      final argument = {
        'message': "Flutter is Future",
      };
      await myChannel.invokeMethod('showShortToastWithArgument');
    } catch (e) {
      print(e);
    }
  }

  _accessData() async {
    final argument = {
      'name': "Trisna",
    };
    final result = await myChannel.invokeMethod('accessData', argument);
    print('result: $result');
  }
}
