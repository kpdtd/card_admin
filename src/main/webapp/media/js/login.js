var Login = function () {
    return {
        init: function () {
           $('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	            	userAccount: {
	                    required: true
	                },
	                userPassword: {
	                    required: true
	                }
	            },
	            messages: {
	            	userAccount: {
	                    required: "用户名不能为空"
	                },
	                userPassword: {
	                    required: "密码不能为空"
	                }
	            },
	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-error', $('.login-form')).show();
	            },
	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },
	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },
	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },
	            submitHandler: function (form) {
	                window.location.href = "./login/login";
	            }
	        });
	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                	App.Ajax.submit('form_sample_1');
	                }
	            }
	        });
	        $('.pull-right').click(function () {
	        	if ($('.login-form').validate().form()) {
	        		App.Ajax.submit('form_sample_1');
	        	}
	        });
        }
    };
}();