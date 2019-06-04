# Navigation-Component

# Tổng quan về Navigation Architecture Component

> Architecture Component xuất hiện trong bộ công cụ và thư viện [Android Jetpack](https://developer.android.com/jetpack/) mới ra mắt trong Google I/O 2018, nhằm mục đích giúp cho việc xây dựng ứng dụng Apdroid trở lên nhanh và dễ dàng hơn.

Thực tế Android Jetpack cung cấp 4 cơ sở hạ tầng lớn như Foundation, Architecture, Behavior và UI. Chúng ta sẽ nghiên cứu về Architecture Navigation trong bài viết dưới đây để hiểu và sử dụng được cấu trúc một ứng dụng mà google đưa ra để giúp đỡ các lập trình viên trong việc quản lý các mô hình ứng dụng của họ.

![](https://www.androidpolice.com/wp-content/uploads/2018/05/chrome_2018-05-08_18-50-02-728x467.png)

> Từ xưa đến nay các ứng dụng Android hiếm khi được tạo nên từ 1 màn hình, vì vậy trải nghiệm mang đến cho người dùng chưa được mượt mà vì phải chuyển qua lại giữa các màn hình. Chính vì vậy navigation được sinh ra để giải quyết vấn đề về giao diện khi chỉ có 1 màn hình và thay vào đó sẽ là các trình diễn của Fragment trên Android. Trên thực tế trước kia mọi người cũng có thể làm những ứng dụng chỉ với một màn hình và nhiều fragment nhưng giờ Google đã cung cấp sẵn các công cụ để tiện lợi cho mọi người khi triển khai ứng dụng.

# Quy tắc của Navigation Architecture

* **Ngăn xếp (stack)**: Trạng thái Navigation trong ứng dụng của bạn nên được trình chiếu lại với nguyên tắc là "last in, first out" giống như cách mà ngăn xếp của Activity quản lý. Ngăn xếp của Navigation sẽ có điểm đến bắt đầu là fragment ở cuối ngăn xếp và điểm đến hiện tại là vị trí trên cùng của stack. Các hoạt động "pushing" hoặc là "popping" luôn luôn được thay đổi ở trên cùng của ngăn xếp.

* **"Up button" không bao giờ thoát khỏi ứng dụng**: Nếu người dùng đang ở điểm đến đầu tiên, phím up sẽ không được hiển thị. Khi ứng dụng của bạn hiển thị mà sử dụng deeplink trên task của một ứng dụng khác(deeplink là 1 URL có thể đưa người dùng đến với đúng nội dung ở trong ứng dụng của bạn), nó sẽ đưa người dùng đến đích thân ứng dụng mở ra nó và không quay trở lại ứng dụng mà deeplink mở ra.

* **"Up button" và "Back button" là tương đương nhau trong task của ứng dụng**: Khi phím back của ứng dụng không thoát khỏi ứng dụng của bạn, như là khi bạn đang thực hiện trên task của bạn mà không phải bắt đầu từ điểm đến bắt đầu của Navigation, khi đó thì phím up và phím back của hệ thống hoạt động giống nhau.

* **Deeplink và Navigation chuyển đến cùng một nơi đến sẽ có cùng một ngăn xếp**: Người dùng có thể bắt đầu với điểm đến đầu tiên và điều hướng nó bằng Navigation để tới đích. Đồng thời người dùng cũng có thể sử dụng deeplink, nếu có sẵn, chúng sẽ được điều hướng đến cùng một điểm đến. Trong 2 cách trên, ngăn xếp của Navigation phải có cùng một ngăn xếp của điểm đến. Đặc biệt, người dùng có thể sử dụng phím up hoặc phím back, bất để là điểm đến là gì, Navigation sẽ điều hướng chúng quay trở về điểm xuất phát. Mọi ngăn xếp tồn tại đều có thể bị xóa và thay vào đó là ngăn xếp của deeplink tạo ra.

# Triển khai

### Yêu cầu chung

* Navigation hiện tại được triển khai trên hai phiên bản Android Studio Preview: [Beta Build 3.2 Beta 4](https://developer.android.com/studio/preview/) và [Canary Build 3.3 Canary 3](https://developer.android.com/studio/preview/). Bạn phải cập nhật Android Studio lên trên 3.2 để có thể sử dụng Navigation, hiện tại bản Beta Build có nhiều lỗi hơn nên khuyên bạn sử dụng bản Android Studio Canary Build.

* Nếu sử dụng bản Canary Build thì chức năng Navigation Editor được bật sẵn, còn nếu bạn sử dụng bản khác thì vào cài đặt và chọn **Experimental** sau đó tích vào **Enable Navigation Editor** để bật chức năng này lên.

### Navigation Graph

> Navigation graph là một biểu đồ cho biết được các thành phần có trong ứng dụng của bạn được Navigation quản lý, có các action và giao diện đầy đủ của các màn hình. Việc này giúp cho ứng dụng của bạn dễ dàng quản lý follow của từng màn hình và liên kết với nhau, nhìn qua là có thể biết được các màn hình ra sao và chúng tương tác với nhau như thế nào.

Navigation graph sẽ trông như thế nào?

![](https://cdn-images-1.medium.com/max/2000/1*YIQHQmS_wneHSl3Ur055OA.png)

### Cài đặt Navigation vào ứng dụng

* Thêm vào build gradle của ứng dụng các dependencies của Navigation

```
dependencies {
    def nav_version = "1.0.0-alpha04"

    implementation "android.arch.navigation:navigation-fragment:$nav_version" // use -ktx for Kotlin
    implementation "android.arch.navigation:navigation-ui:$nav_version" // use -ktx for Kotlin

    // optional - Test helpers
    androidTestImplementation "android.arch.navigation:navigation-testing:$nav_version" // use -ktx for Kotlin
}
```

### Tạo file Navigation Graph

* Đầu tiên bạn phải tạo file ***nav_graph.xml*** như tạo file xml bình thường chú ý chọn **Resource-type** là **Navigation**.

* Chọn Design sẽ thấy được toàn bộ các fragment được tạo ra và liên kết với nhau như đây.

![](https://developer.android.com/images/topic/libraries/architecture/navigation-editor.png)

* Chúng ta có thể thấy Navigation Graph được chia thành 3 phần khác nhau:

1. Phần này hiển thị tất cả các destination có trong graph và đâu là nơi chưa navigation graph hosted được khởi tạo.

2. Phần thứ 2 rất lớn để bạn có thể trình diễn tất cả các destination có trong graph và quan hệ giữa chúng được biểu diễn rất trực quan. Dữ liệu của bạn cũng sẽ hiển thị ở đây như một bản tóm tắt các màn hình có trong graph của bạn.

3. Phần thứ 3 chứa các thuộc tính của destination. Nội dung phần này chỉ hiển thị khi bạn chỉ định destination hoặc là action của nó, có thể sửa đổi các thuộc tính các chỉ mục của action hoặc destination.

### Thêm mới một Destination

* Bạn có thể tạo mới Fragment bằng cách click vào ![](https://developer.android.com/studio/images/buttons/navigation-add.png) hoặc là tạo fragment rồi mới chọn thêm để hiển thị ra danh sách các fragment có trong ứng dụng muốn sử dụng navigation. Bắt buộc navigation phải có một fragment được chọn là start (màn hình hiển thị đầu tiên của navigation), được định nghĩa ở trong xml navigation là id của fragment đầu tiên.

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:android="http://schemas.android.com/apk/res/android"
    app:startDestination="@id/blankFragment">
    <fragment
        android:id="@+id/blankFragment"
        android:name="com.example.cashdog.cashdog.BlankFragment"
        android:label="Blank"
        tools:layout="@layout/fragment_blank" />
</navigation>
```

![](https://images.viblo.asia/0a20bc34-b3d9-4b22-a301-5ac3e41a024b.png)

### Lưu trữ Navigation Graph

* Chỉ định cho activity thấy đó là host của navigation trong file xml của activity bằng thuộc tính **app:defaultNavHost="true"**. Đây sẽ là nơi xử lý tất cả các thành phần thuộc về Navigation Graph.

```
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/navigation_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <fragment
        android:id="@+id/my_nav_home_fragment"
        class="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph" />
</FrameLayout>
```


* Cũng có thể chỉ định layout này chính là host của navigation bằng cách override hàm **onSupportNavigateUp()** để chỉ định activity chính là nơi để lưu trữ các destination. Cách này thì có thể dùng để chỉnh sửa các navigation graph khác nhau cho 1 activity.

```
override fun onSupportNavigateUp(): Boolean {
    return Navigation.findNavController(this, R.id.my_nav_home_fragment).navigateUp()
  }
```

### Liên kết giữa các destination

* Để tạo hành động giữa các destination mới nhau để chuyển màn hay gửi dữ liệu gì thì có thể kéo thả ở trên từ nơi bắt đầu tới nơi kết thúc trên Graph Editor, sẽ được thẻ **action** ở trong destination đó, quản lý thẻ đó qua id để có thể gọi action đó từ destination. Có thêm xử lý về animation, flag hoặc là laucher-mode để có thể xử lý cho destination sẽ được mở tiếp theo.

```
<fragment
        android:id="@+id/blankFragment"
        android:name="com.example.cashdog.cashdog.BlankFragment"
        android:label="fragment_blank"
        tools:layout="@layout/fragment_blank" >
        <action
            android:id="@+id/action_blankFragment_to_blankFragment2"
            app:clearTask="false"
            app:destination="@id/detailFragment"
            app:enterAnim="@anim/nav_default_enter_anim"
            app:exitAnim="@animator/flip_y"
            app:launchDocument="false"
            app:launchSingleTop="false"
            app:popEnterAnim="@animator/flip_x"
            app:popExitAnim="@anim/nav_default_pop_exit_anim"
            app:popUpToInclusive="false"
            />
    </fragment>
```

* Để di chuyển sang destination khác, chúng ta cần sử dụng **NavHostFragment** để có thể tìm được **NavController**. NavController chịu trách nhiệm quản lý toàn bộ quá trình điều hướng trong Navigaiton Host. Tìm đến đúng id của destination đó để có thể chuyển được sang destination khác.

```
 NavHostFragment.findNavController(this).navigate(R.id.blankFragment2)
```

### Truyền gửi dữ liệu giữa các destination

Truyền gửi dữ liệu giữa các destination thông thường sử dung Bundle để lưu trữ, NavController cho phép truyền thêm tham số là Bundle sang bên destination khác.
> Lợi ích của việc truyền gửi này là có thể gửi qua lại rất nhiều các dạng dữ liệu từ nguyên thủy đến nâng cao, nhưng mà việc kiểm soát lỗi thì rất khó để kiểm soát nếu người dùng gửi sai dữ liệu. Chính vì vậy đây là 1 trong 2 cách có thể sử dụng để truyền gửi dữ liệu khi sử dụng Navigation Component.

* Thêm thuộc tính **agurment** ở bên destination nhận với tham số **name** và **defaultValue**:

```
 <fragment
        android:id="@+id/detailFragment"
        android:name="android.thaihn.kotlindemo.screen.navigation.DetailFragment"
        android:label="fragment_detail"
        tools:layout="@layout/fragment_detail">

        <argument
            android:name="name"
            android:defaultValue="Thaihn" />
    </fragment>
```

* Xử lý ở destination gửi chỉ cần tạo 1 bundle và truyền nó quá NavController như sau:

```
 var bundle = Bundle()
 bundle.putString("name", "Thaihn")
 NavHostFragment.findNavController(this).navigate(R.id.detailFragment, bundle)
```

* Xử lý bên destination nhận chỉ cần getArgument ra là được

```
var name = arguments.getString("name")
```

### Truyền gửi dữ liệu một cách an toàn hơn - args plugin của Nagivation Architecture Architecture

Đây là một cách truyền gửi dữ liệu an toàn hơn, được xem là giải pháp tốt hơn sử dụng Bundle bình thường. Safe args được xây dựng dựa trên nền tàng Bundle nhưng yêu cầu mã nguồn nhiều hơn để đổi lấy sự an toàn hơn khi truyền gửi dữ liệu trong ứng dụng của bạn.
> Hạn chế của cách gửi dữ liệu kiểu này là chỉ hỗ trợ các dạng dữ liệu cơ bản là "**inferred, string, integer, reference**" nên chúng ta phải cân nhắc trước khi sử dụng. Nếu cần truyền gửi dữ liệu phức tạp thì nên sử dụng gửi Bundle một cách thủ công.

* Thêm plugin vào file build gradle như sau:

```
apply plugin: 'com.android.application'
apply plugin: 'androidx.navigation.safeargs'

android {
   //...
}
```

```
build.gradle project

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0-alpha04"
    }
}
```

* Khi sử dụng plugin args thì khi truyền gừi dữ liệu qua argument ở xml sẽ thêm 1 thuộc tính **app:argType** để kiểm soát phải gửi dữ liệu theo kiểu nào, sẽ không có gửi sai dữ liệu nếu như sử dụng args, nhờ đó mà ứng dụng của chúng ta có ít lỗi hơn khi phát hiện gửi sai ngay ở lúc compile time.

```
<fragment
        android:id="@+id/detailsFragment"
        android:name="thaihn.com.navigationcomponent.DetailsFragment"
        android:label="fragment_details"
        tools:layout="@layout/fragment_details">

        <argument
            android:name="name"
            android:defaultValue="NoName"
            app:argType="string" />
    </fragment>
```

* Khi gửi dữ liệu thì plugin này tự động tạo ra class YourFragmentDirection để kiểm soát việc gửi dữ liệu qua id của action bạn đặt cho mỗi action khác nhau. Qua đó có thể kiểm soát và gửi dữ liệu 1 các đúng kiểu để tránh lỗi. Chú ý nếu thay đổi kiểu của args thì phải rebuild project để có thể cập nhật class tự tạo ra của args.

```
val directions = MainFragmentDirections
                .actionMainFragmentToDetailsFragment().setName("Hoang Ngoc Thai")
        NavHostFragment.findNavController(this).navigate(directions)
```

* Khi nhận dữ liệu thì args cũng tự động tạo ra class YourFragmentArgs để có thể nhận bundle và trả về dữ liệu đã được định nghĩa ở trong xml graph navigation.

```
mText = rootView.findViewById(R.id.text_details)
val name = DetailsFragmentArgs.fromBundle(arguments).name
mText?.text = name
```

## Liên kết Navigation với các thành phần khác

Cho đến giờ thì những ví dụ cũng chỉ là cách tạo Navigation một cách thủ công, vì gần như không kết hợp với các thành phần khác có sẵn của Android cung cấp. Mọi thứ đều không sao cho đến khi bạn cần sử dụng Navigation Drawer, Bottom Navigation View, Toolbars thì nhu cầu kết hợp chúng với nhau là rất cần thiết. Những hàm hỗ trợ liên kết với các thành phần khác nằm trong lớp NavigationUI.

> Việc này sử dụng các thành phần điều hướng khác mà không phải là tạo **action** của destination, mà chính là sử dụng điều hướng có sẵn ở trong các trình điều hướng cao cấp hơn như NavigationView, Bottom Navigation.

### Kết hợp Navigation với NavigationView hoặc Bottom Navigation View

Như đã đề cập, việc sử dụng trình điều hướng của thành phần khác được cung cấp bởi lớp NavigationUI bằng cách gọi hàm tĩnh của lớp này sau đây:

```
Navigation.findNavController(this, R.id.nav_host_component)?.let { navigation ->
    NavigationUI.setupWithNavController(bottomNavigation, navigation)
}
```

* Việc này có yêu cầu nhỏ khi thiết kế là id đặt trong file nav_graph phải trùng với id của menu mà NavigationView hoặc Bottom Navigation View sử dụng để điều khiển các sự kiện. Sau khi cài đặt xong thì tất cả các sự kiện của Navigation View sẽ được Navigation Controller xử lý.

* Giao diện của activity kết hợp giữa các thành phần kéo các fragment vào trong giao diện của activity như sau:

```
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <fragment
        android:id="@+id/nav_host_github"
        class="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toTopOf="@id/navigation"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/github_nav_graph" />


    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/navigation"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="0dp"
        android:layout_marginEnd="0dp"
        android:background="?android:attr/windowBackground"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/navigation" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Navigation kết hợp với Custom View

* Vì NavigationUI chỉ cung cấp các tùy chỉnh có giới hạn các thành phần giao diện người dùng, vì vậy nếu muốn tự tạo Custom View nào đó của mình, bạn phải tự xây dựng các chức năng của mình.

* Để sử dụng bạn có thể sử dụng **addOnNavigatedListener()** để có thể lắng nghe được sự kiện khi mà Navigation điều hướng các destination. Sử dụng nó nếu như bạn muốn thay đổi các destination đích hoặc là ẩn hiện các thành phần giao diện khác.

## Kết hợp nhiều Navigation Graph

Trên thực tế trong ứng dụng có rất nhiều phần riêng biệt mà nếu để chung chúng trong 1 navigation graph thì rất phức tạp và rối bởi đồ thị quá nhiều phần. Chính vì thế cách giải quyết trong trường hợp này là nhóm các navigation lại với nhau và có quan hệ lồng nhau.

### Nhóm các navigation graph vào navigation khác

Biểu đồ chứa được gọi là "**root graph**" và biểu đồ con trong đó được gọi là "**nested graph**", việc này rất tiện dụng cho việc nhóm các thành phần giao diện riêng biệt thực hiện các chức năng riêng biệt như là luồng đăng nhập và đăng ký tài khoản.

![](https://developer.android.com/images/topic/libraries/architecture/navigation-migrate3.png)
> Biểu đồ lồng nhau sẽ đóng gói các destination của nó vào 1 gói, và các biểu đồ khác tương tác với biều đồ lồng nhau qua điểm bắt đầu của biểu đồ đó.

Để tạo biểu đồ lồng nhau bạn làm các bước sau:

* Từ biểu đồ gốc, tiến hành thêm mới graph hoặc là **include** graph đã có sẵn vào biểu đồ chính. Lưu ý navigation được thêm vào bắt buộc phải được chỉ định đâu là **startDestination**.

* Chọn vào biểu đồ mới thêm, click chuột phải và chọn **Move To Nested Graph** -> **New Graph**. Lúc này graph này được coi như là 1 destination bình thường, bạn có thể gửi dữ liệu qua graph mới include vào bằng action và sẽ gửi đển destination start mặc định của graph.
```
....
    <fragment
        android:id="@+id/mainFragment"
        android:name="thaihn.com.navigationcomponent.ui.main.weather.WeathersFragment"
        android:label="fragment_main"
        tools:layout="@layout/fragment_weathers">
        <action
            android:id="@+id/toDetailWeather"
            app:destination="@id/detailsFragment" />
        <action
            android:id="@+id/action_test"
            app:destination="@id/github_nav_graph" />
    </fragment>

    <include app:graph="@navigation/github_nav_graph" />
....
```

> Coi id destination của action chính là id của graph chúng ta include vào để có thể chuyển đến graph khác trong cùng 1 activity và thực hiên một luồng mới riêng biệt.

### Navigation giữa các Activity

Mặc dù google giới thiệu và gợi ý mô hình Navigation với một Activity duy nhất, nhưng nó không phải là mô hình duy nhất mà các ứng dụng thường sử dụng. Nếu ứng dụng của bạn đang phát triển và gặp phải vấn đề này thì chúng ta vẫn sẽ có cách giải quyết.
> Nếu không bắt buộc thì nên sử dụng theo cách trên, kéo các navigation graph về hết 1 màn hình để chuyển các navigation graph thay vì phải tạo 1 activity mới.

![](https://developer.android.com/images/topic/libraries/architecture/navigation-migrate1.png)
Như trên, mỗi activity đều có những navigation graph riêng và các destination trong đó. Để giải quyết trường hợp này, chúng ta có thể dùng sự kiện click bình thường để mở ra activity mới mà không qua navigation graph, lúc này sử dụng **startActivity(intent)** để mở ra activity mới. Nhưng cách đó là thủ công và không nên sử dụng. Thay vào đó chúng ta sẽ sử dụng Navigation với Activity thay vì Fagment như các phần trước giới thiệu.

* Tạo một destination mới của đồ thì gốc chính là activity mà bạn muốn chuyển đến bằng cách thêm mới trong navigation editor. Đồ thị gốc sẽ trông như sau:

```
....
    <activity
        android:id="@+id/testActivity"
        android:name="thaihn.com.navigationcomponent.ui.main.TestActivity"
        android:label="activity_test"
        tools:layout="@layout/activity_test" />
</navigation>
```

* Sau đó tạo action để có thể di chuyển đến màn hình đó như sau:

```
 <fragment
        android:id="@+id/mainFragment"
        android:name="thaihn.com.navigationcomponent.ui.main.weather.WeathersFragment"
        android:label="fragment_main"
        tools:layout="@layout/fragment_weathers">

        <action
            android:id="@+id/action_test_activity"
            app:destination="@id/testActivity" />
    </fragment>
```

* Sau đó tiến hành di chuyển đến activity đó bằng action đã đặt ở trong navigation graph là được:

```
val directions = WeathersFragmentDirections.actionTestActivity()
NavHostFragment.findNavController(this).navigate(directions)
```

### Điều hướng lên từ Activity destination

Khi activity destination có nhu cầu muốn điều hướng ngược lại trở về activity gốc mà đã gọi ra nó, điều này đôi lúc rất cần thiết cho chúng ta. Để làm được điều này, phải thông qua các bước sau đây:

* Định nghĩa thẻ parent activity ở trong tập tin AndroidManifest.xml

```
    <activity
        android:name=".ui.main.TestActivity"
        android:theme="@style/Theme.AppCompat.NoActionBar">

        <meta-data
            android:name="android.support.PARENT_ACTIVITY"
            android:value=".ui.main.MainActivity" />
    </activity>
```

* Để chuyển hướng lên về activity trước, bạn phải lắng nghe sự kiện của phím up của hệ thống để nhận biết click vào phím up. Và sử dụng class **NavUtils** để chuyển hướng lên, điều hướng hoạt động về activity parent của nó, trong gần hết các trường hợp chỉ cần gọi hàm **navigateUpFromSameTask** :

```
toolbar.setNavigationOnClickListener {
            val upIntent: Intent? = NavUtils.getParentActivityIntent(this)
            when {
                upIntent == null -> throw IllegalStateException("No Parent Activity Intent") as Throwable
                NavUtils.shouldUpRecreateTask(this, upIntent) -> {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities()
                }
                else -> {
                    NavUtils.navigateUpTo(this, upIntent)
                }
            }
        }
```
> Tuy nhiên nếu activity này được mở ra bằng deeplink thì việc này sẽ giúp chúng ta thoát khỏi ứng dụng. Để khắc phục trường hợp đó chúng ta phải kiểm tra và tạo lại task với parent activity và điều hướng nếu cần.
>

## Một số thuộc tính khác

### Pop Behavior
Thông thường thì khi nhấn phím back hoặc phím up thì Navigation Component sẽ mặc định trở về destination trước đó, đôi lúc bạn cần trở về 1 destination trước đó nữa thì việc này chúng ta phải sử dụng đến **Pop Behavior** được Navigation Component hỗ trợ.

![](https://images.viblo.asia/78d63968-ef05-48f0-87ce-43130d1870b9.png)

Có thể thấy như trên hình chúng ta có thể chọn lựa được sẽ chuyển về destination nào, và việc này cũng sẽ xóa hết các stack của destination tồn tại giữa chúng.

### Deeplink

> Đối với 1 số bạn chưa biết về Deeplink thì có thể tham khảo định nghĩa của google develop cung cấp [tại đây](https://developer.android.com/training/app-links/). Nói một cách ngắn ngọn hơn thì deeplink giúp việc truy cập đúng nội dung bên trong ứng dụng của bạn dễ dàng và thuận tiện hơn mà không phải trải qua các thành phần khác.
>
> Hiện nay việc sử dụng deeplink đã được Firebase hỗ trợ rất nhiều thông qua [Firebase Dynamic Link](https://firebase.google.com/docs/dynamic-links/), các bạn nên chuyển qua sử dụng nền tảng của Firebase để được hỗ trợ tốt nhất.

Mình sẽ hướng dẫn các bạn sử dụng Deeplink bằng cách thủ công mà Navigation Component đã cung cấp. Mỗi 1 desnination đều sẽ có 1 thẻ để thêm deeplink vào như hình dưới đây:

![](https://images.viblo.asia/bfea4276-ce9c-4c0c-b813-e7edd27d6ad3.png)

Tiến hành thêm link để truy cập vào destination cụ thể để có thể mở ra nội dung cần thiết đối với người dùng:
![](https://images.viblo.asia/6c32e73c-07a6-44a2-8da8-31133777e730.png)

Sau khi đã thêm đường dẫn của deeplink thì các bạn có thể test được ngay trên terminal của mình, thông qua adb shell như sau:

![](https://images.viblo.asia/e4c84c85-02a0-419a-8884-b9299249faf6.PNG)

Như vậy là các bạn đã có thể tạo được 1 deeplink đơn giản đối với Navigation Component.