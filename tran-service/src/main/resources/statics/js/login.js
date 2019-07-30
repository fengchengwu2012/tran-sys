$(function () {
    let vm = new Vue({
        el: '#tran-app',
        data: {
            username: 'admin',
            password: 'admin',
            captcha: '',
            error: false,
            errorMsg: '',
            src: 'captcha.jpg'
        },
        beforeCreate: function () {
            if (self != top) {
                top.location.href = self.location.href;
            }
        },
        methods: {
            refreshCode: function () {
                this.src = "captcha.jpg?t=" + $.now();
            },
            login: function (event) {
                let data = "username=" + vm.username + "&password=" + vm.password + "&captcha=" + vm.captcha;
                $.ajax({
                    type: "POST",
                    url: "sys/login",
                    data: data,
                    dataType: "json",
                    success: function (result) {
                        if (result.code === 0) {
                            parent.location.href = 'index.html';
                        } else {
                            vm.error = true;
                            vm.errorMsg = result.msg;
                            vm.refreshCode();
                        }
                    }
                });
            }
        }
    });

})