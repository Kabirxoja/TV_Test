It displays all installed applications in a Leanback-style grid layout and allows the user to launch apps directly.  
It also remembers the **last opened app** and displays it at the top of the list for a better user experience.

---

## 🧩 Features

✅ **Launcher Functionality**
- Works as a full **Home Launcher** (with `HOME` and `LAUNCHER` intent filters).  
- Compatible with **Android TV**, **Google TV**, and **Mobile devices**.

✅ **App List**
- Displays all installed and launchable apps.  
- Shows **App icon** and **App name**.  
- Launches selected apps instantly using `startActivity()`.

✅ **Leanback UI**
- Uses **Leanback library** components such as `VerticalGridSupportFragment` and `ArrayObjectAdapter`.  
- Optimized for Android TV navigation and D-pad focus.

✅ **State Saving**
- Saves the **last launched app** using `SharedPreferences`.  
- The previously opened app appears **first** in the app list upon relaunch.

✅ **Multi-Device Support**
- Tested and works on:
  - Android TV Emulator  
  - Google TV  
  - Android Phone (for testing via `LAUNCHER` intent)

---

## 🧠 Technical Stack

| Component | Technology |
|------------|-------------|
| **Language** | Kotlin |
| **UI Library** | Leanback |
| **Architecture** | Activity + Fragment (TV UI pattern) |
| **Storage** | SharedPreferences |
| **minSdkVersion** | 21 |
| **targetSdkVersion** | 35 |

Using the Leanback UI toolkit
https://developer.android.com/training/tv/playback/leanback?utm

ArrayObjectAdapter
https://developer.android.com/reference/androidx/leanback/widget/ArrayObjectAdapter?utm

VerticalGridSupportFragment
https://developer.android.com/reference/androidx/leanback/app/VerticalGridSupportFragment?utm_source=chatgpt.com
