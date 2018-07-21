// 更新数据状态
function updateState(id, value, description) {
	bootbox.confirm(description, function(result) {
		if (result) {
			$.ajax({
				type : 'POST',
				url : './updateState',
				dataType : "text",
				data : {
					id : id,
					state : value
				},
				success : function(data) {
					initPage();
				}
			})
		}
	});
}

function getDictionaryJSON(key) {
	var s;
	$.ajax({
		type : 'POST',
		url : "../dictionary/getDictionaryByKey",
		dataType : "json",
		async : false,
		data : {
			key : key
		},
		success : function(obj) {
			s = obj;
		}
	});
	return s;
}

function cutString(str, len) {
	// length属性读出来的汉字长度为1
	if (str.length * 2 <= len) {
		return str;
	}
	var strlen = 0;
	var s = "";
	for (var i = 0; i < str.length; i++) {
		s = s + str.charAt(i);
		if (str.charCodeAt(i) > 128) {
			strlen = strlen + 2;
			if (strlen >= len) {
				return s.substring(0, s.length - 1) + "...";
			}
		} else {
			strlen = strlen + 1;
			if (strlen >= len) {
				return s.substring(0, s.length - 2) + "...";
			}
		}
	}
	return s;
}

/**
 * 将秒数换成时分秒格式
 */
function formatSeconds(value) {
	var theTime = parseInt(value);// 秒
	var hourTime = parseInt(theTime / 3600);// 小时
	var minuteTime = parseInt((theTime % 3600) / 60);// 分钟
	var secondTime = parseInt(theTime % 3600 % 60);// 秒
	var result = '';
	result += (hourTime < 10 ? '0' + hourTime : hourTime);
	result += (':' + (minuteTime < 10 ? '0' + minuteTime : minuteTime));
	result += (':' + (secondTime < 10 ? '0' + secondTime : secondTime));
	return result;
}

function getCode(type) {
	var date = new Date();
	var fullYear = date.getFullYear();
	var year = fullYear.toString().substr(2, 2); // 获取年的最后两位
	var currentTimeSeconds = date.getMilliseconds().toString(); // 获取当前毫秒数
	var lackLength = 6 - (year + currentTimeSeconds).length; // 获取剩余需要的长度
	var random = 0;
	if (lackLength == 1) {
		random = Math.round(Math.random() * 9).toString();
	} else if (lackLength == 2) {
		random = Math.round(Math.random() * 99).toString();
	} else {
		random = Math.round(Math.random() * 999).toString();
	}
	var code = year + currentTimeSeconds + random;
	var codeLength = code.length;
	if (codeLength == 4) {
		code += "00";
	} else if (codeLength == 5) {
		code += "0";
	}
	return type + code;
}

var formatDateTime = function(obj, IsMi) {
	var myDate = new Date(obj);
	var year = myDate.getFullYear();
	var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
	var day = ("0" + myDate.getDate()).slice(-2);
	var h = ("0" + myDate.getHours()).slice(-2);
	var m = ("0" + myDate.getMinutes()).slice(-2);
	var s = ("0" + myDate.getSeconds()).slice(-2);
	var mi = ("00" + myDate.getMilliseconds()).slice(-3);
	if (IsMi == true) {
		return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
	} else if (IsMi == false) {
		return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s
				+ "." + mi;
	} else {
		return year + "-" + month + "-" + day
	}
};