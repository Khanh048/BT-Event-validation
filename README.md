# BT Event Validation

Ứng dụng Android gồm hai Activity **Đăng ký** và **Chi tiết**, được viết bằng Kotlin theo nội dung Lecture 5: Events + Validation.

## Chức năng

- Nhập họ tên, email, số điện thoại, mã sinh viên và lớp.
- Kiểm tra tất cả trường cùng lúc và hiển thị lỗi ngay dưới ô nhập bằng `TextInputLayout`.
- Tự xóa lỗi cũ khi người dùng sửa nội dung.
- Khi dữ liệu hợp lệ, hiển thị Snackbar **Đăng ký thành công!**.
- Nút **Xem chi tiết** trên Snackbar dùng explicit Intent để mở Activity Chi tiết và truyền đủ dữ liệu.
- Nút **Quay lại đăng ký** trên Activity Chi tiết dùng explicit Intent để trở về form.

## Quy tắc validation

| Trường | Quy tắc |
|---|---|
| Họ và tên | Bắt buộc, có ít nhất 2 từ, chỉ gồm chữ cái và ký tự tên hợp lệ |
| Email | Bắt buộc, đúng định dạng email Android |
| Số điện thoại | Bắt buộc, đúng 10 chữ số và bắt đầu bằng 03/05/07/08/09 |
| Mã sinh viên | Bắt buộc, từ 5–15 chữ cái hoặc chữ số, không có khoảng trắng |
| Lớp | Bắt buộc, từ 2–30 ký tự, không chứa ký tự đặc biệt không hợp lệ |

## Công nghệ

- Kotlin
- Android XML Views và ViewBinding
- Material Components: `TextInputLayout`, `TextInputEditText`, `MaterialButton`, `Snackbar`
- Android Gradle Plugin 9.4.0, Gradle 9.6.0, compile/target SDK 37, min SDK 24

## Mở và chạy bằng Android Studio

1. Mở Android Studio, chọn **Open**.
2. Chọn thư mục gốc `BT-Event-validation` (thư mục chứa `settings.gradle.kts`).
3. Chờ Gradle Sync hoàn tất.
4. Chọn máy ảo hoặc điện thoại Android đã bật USB debugging.
5. Nhấn **Run app**.

Có thể build APK bằng Command Prompt tại thư mục dự án:

```bat
gradlew.bat :app:assembleDebug
```

APK debug được tạo tại `app/build/outputs/apk/debug/app-debug.apk`.

## Dữ liệu dùng để quay clip nộp bài

### Case lỗi

1. Bấm **Đăng ký** khi tất cả trường trống để thấy lỗi bắt buộc.
2. Nhập các giá trị sai rồi bấm **Đăng ký**:

| Trường | Giá trị sai mẫu | Lý do |
|---|---|---|
| Họ và tên | `Khanh` | Chỉ có 1 từ |
| Email | `khanh@` | Sai định dạng email |
| Số điện thoại | `0212345678` | Sai đầu số di động |
| Mã sinh viên | `SV 123` | Có khoảng trắng |
| Lớp | `DHTH@` | Có ký tự đặc biệt không hợp lệ |

### Case thành công

Nhập dữ liệu mẫu:

- Họ và tên: `Võ Quốc Khánh`
- Email: `khanh@example.com`
- Số điện thoại: `0912345678`
- Mã sinh viên: `2212345`
- Lớp: `22DTHA1`

Bấm **Đăng ký**, giữ Snackbar trong khung hình, sau đó bấm **Xem chi tiết**. Quay màn hình Chi tiết hiển thị đủ 5 giá trị và bấm **Quay lại đăng ký** để chứng minh điều hướng hai chiều.

## Cấu trúc chính

```text
app/src/main/
├── java/com/khanh/bteventvalidation/
│   ├── detail/DetailActivity.kt
│   ├── model/StudentRegistration.kt
│   ├── registration/RegistrationActivity.kt
│   └── validation/FormValidator.kt
├── res/layout/
│   ├── activity_detail.xml
│   └── activity_registration.xml
└── AndroidManifest.xml
```

## Lưu ý repository

File giáo trình `Lecture_5_Events_Validation_2026.pdf`, video quay màn hình và các file build cục bộ được loại trừ bằng `.gitignore`, không được đẩy lên GitHub.

Repository: <https://github.com/Khanh048/BT-Event-validation>

Tham khảo cấu hình build: [Android Gradle Plugin release notes](https://developer.android.com/build/releases/gradle-plugin) và [built-in Kotlin từ AGP 9.0](https://developer.android.com/build/migrate-to-built-in-kotlin).
