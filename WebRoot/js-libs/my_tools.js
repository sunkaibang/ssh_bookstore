/*这个方法是要在页面初始化调用的
	<select id="year" name="birthday">
				<option value="none">请选择</option>
			</select>年
			
			<select id="month" name="birthday">
				<option value="none">请选择</option>
			</select>月
			
			<select id="day" name="birthday">
				<option value="none">请选择</option>
			</select>日 <br/>
*/
function initDate() {
	var yearNode = document.getElementById("year");
	var monthNode = document.getElementById("month");
	var dayNode = document.getElementById("day");

	//得到现在的年，添加年份
	var reallyYear = new Date().getFullYear();
	yearNode.length = 1;
	for(var i = reallyYear; i >= reallyYear - 50; i--) {
		var yearChildNode = document.createElement("option");
		yearChildNode.innerHTML = i;
		yearChildNode.value = i;
		yearNode.appendChild(yearChildNode);
	}

	//添加月份
	monthNode.length = 1;
	for(var i = 1; i <= 12; i++) {
		var monthChildNode = document.createElement("option");
		monthChildNode.innerHTML = i;
		monthChildNode.value = i;
		monthNode.appendChild(monthChildNode);
	}

	yearNode.onchange = function() {
		changeDay();
	}
	monthNode.onchange = function() {
		changeDay();
	}

}

function changeDay() {
	var yearNode = document.getElementById("year");
	var monthNode = document.getElementById("month");
	var dayNode = document.getElementById("day");
	var selectedYear = 0;
	var selectedMonth = 0;
	var selectedDay = 0;

	dayNode.length = 1;
	var endDay; //计算天数
	if(yearNode.value % 400 == 0 || (yearNode.value % 4 == 0 && yearNode.value % 100 != 0)) {
		if(monthNode.value == 2) {
			endDay = 29;
		}
	} else {
		if(monthNode.value == 2) {
			endDay = 28;
		}
	}
	if(monthNode.value == 4 || monthNode.value == 6 || monthNode.value == 9 || monthNode.value == 11) {
		endDay = 30;
	} else if(monthNode.value == 2) {

	} else {
		endDay = 31;
	}

	for(var i = 1; i <= endDay; i++) {
		var dayChildNode = document.createElement("option");
		dayChildNode.innerHTML = i;
		dayChildNode.value = i;
		dayNode.appendChild(dayChildNode);
	}

}

/*
	加载上传图片给Img标签
	<input id="img_input" type="file" />
	<img id="img" />
*/

function loadImgFromInputFile(width, height) {

	var h = height;
	var w = width;
	var input = document.getElementById("img_input");
	var imageType = /image.*/;
	var getOnloadFunc = function(aImg) {

		return function(evt) {
			var data = evt.target.result;
			var imgObject = new Image();
			imgObject.src = data;
			imgObject.onload = function() {
				var width = imgObject.width;
				var height = imgObject.height;
				var fileSize = imgObject.fileSize;

//				var oldBiLi = width / height;
//				var newBili = w / h;
//				if(oldBiLi > newBili) {
//					var h1 = parseInt(height * w / width);
//					aImg.width = w;
//					aImg.height = h1;
//				} else {
//					var w2 = parseInt(width * h / height);
//					aImg.width = w2;
//					aImg.height = h;
//				}
				aImg.width = w;
				aImg.height = h
			}
			aImg.src = data;
		};
	}
	input.addEventListener("change", function(evt) {
		for(var i = 0, numFiles = this.files.length; i < numFiles; i++) {
			var file = this.files[0];
			if(!file.type.match(imageType)) {
				continue;
			}
			var img = document.getElementById("img");

			var reader = new FileReader();
			reader.onload = getOnloadFunc(img);
			reader.readAsDataURL(file);

		}
	}, false);

}


