/**
 * 拍照上传图片
 */
var indexObj = {};

indexObj.tips = "没有检测到设备，请确保开启摄像头。";

// begin 显示摄像头的录像。
navigator.getUserMedia = navigator.getUserMedia ||
    navigator.webkitGetUserMedia ||
    navigator.mozGetUserMedia;

if (navigator.getUserMedia) {
    navigator.getUserMedia({ audio: true, video: { width: 800, height: 450 } },
        function(stream) {
            var video = document.getElementById("index__video");
            video.srcObject = stream;
            console.log("stream active", stream)
            video.onloadedmetadata = function(e) {
                video.play();
            };
        },
        function(err) {
            console.log("The following error occurred: " + err.name);
            document.getElementById("index__tips").innerHTML = indexObj.tips;
        }
    );
} else {
    console.log("getUserMedia not supported");
    document.getElementById("index__tips").innerHTML = indexObj.tips;
}
// end 显示摄像头的录像。


