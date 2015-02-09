var signUpBtn = $('sign_up_btn');

signUpBtn.on('click', function() {

signUpBtn.animator({
    properties: {
        alpha: [0, 1],
        scale: [0, 1, 0.5, 1]
    },
    interpolator: '@android:interpolator/linear',
    duration: 1000
}).start()
})

var signInBtn = $('sign_in_btn');

signInBtn.on('click', function() {
openScreen('activity_pinned_food');
    signInBtn.animator('rotate').start()
})