function setLinks() {
    // 使用AJAX从后台获取数据
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 解析后台返回的数据
            var data = JSON.parse(xhr.responseText);

            // 获取包含超链接的div元素
            var linkDiv = document.getElementById("myDiv");

            // 遍历数据中的每个对象
            for (var i = 0; i < data.length; i++) {
                // 创建一个新的超链接元素
                var link = document.createElement("a");
                link.href = data[i].url; // 设置超链接的URL
                link.textContent = data[i].name; // 设置超链接的文本内容

                // 将超链接元素添加到div中
                linkDiv.appendChild(link);
            }
        }
    };
    xhr.open("GET", "backend_data.json", true);
    xhr.send();
}

function showAlert() {
    alert("您已经在首页了！");
}

var currentImageIndex = 0;
var images = [
    "./images/index/image1.jpg",
    "./images/index/image2.jpg",
    "./images/index/image3.jpg"
];

function showImage() {
    var slider = document.querySelector(".owl-carousel");
    var img = slider.querySelector("img");
    img.src = images[currentImageIndex];
}

function previousImage() {
    currentImageIndex--;
    if (currentImageIndex < 0) {
        currentImageIndex = images.length - 1;
    }
    showImage();
}

function nextImage() {
    currentImageIndex++;
    if (currentImageIndex >= images.length) {
        currentImageIndex = 0;
    }
    showImage();
}

// 初始化显示第一张图片
showImage();
