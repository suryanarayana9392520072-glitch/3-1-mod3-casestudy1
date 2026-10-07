# 3-1 Agentic SDC Mod 3 Case 1

Complete Android Studio Kotlin/XML project with a Python Flask backend.

## Android
- Kotlin
- XML layouts
- ViewBinding
- Material 3
- RecyclerView
- Retrofit
- Coroutines
- `values`
- `values-night`
- `xml`
- `drawable`
- AndroidManifest

## Python backend

Open `python_backend`.

### Windows
Run:

```text
run_windows.bat
```

Or manually:

```text
python -m venv .venv
.venv\Scripts\activate
pip install -r requirements.txt
python app.py
```

Backend:
`http://127.0.0.1:5000`

Health test:
`http://127.0.0.1:5000/health`

## Android Emulator

The Android app uses:

`http://10.0.2.2:5000/`

This connects the emulator to Python running on the same PC.

## Physical phone

Open:

`app/src/main/java/project/case1/network/RetrofitClient.kt`

Change `10.0.2.2` to your computer's local IPv4 address, for example:

`http://192.168.1.10:5000/`

Make sure the phone and PC use the same Wi-Fi and Windows Firewall allows Python.
