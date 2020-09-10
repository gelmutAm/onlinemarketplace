$('.signin-form').submit(e => {
    e.preventDefault();

    const emailMessage = 'Email is not valid!';
    const passwordMessage = 'Password must be at list 8 characters including a number, uppercase and lowercase letters.';
    const confirmPasswordMessage = 'Please make sure your passwords match.';

    const emailInput = e.target[0];
    const passwordInput = e.target[1];

    const isEmailValid = (emailInput.value.length === 0) ? false : true;
    const isPasswordValid = (passwordInput.value.length === 0) ? false : true;

    if (isEmailValid && isPasswordValid) {
        let credentials = {
            login: emailInput.value,
            password : passwordInput.value
        };

        fetch('/api/marketplace/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials),
        }).then((response) => {
            if (response.ok) {
                hideModal('.signin-modal-wrapper');
                emailInput.value = '';
                passwordInput.value = '';
            } else {
                e.target.reportValidity();
            }
        });

    } else {
        switch (true) {
            case !isEmailValid : emailInput.setCustomValidity(emailMessage); break;
            case !isPasswordValid : passwordInput.setCustomValidity(passwordMessage); break;
            case !doPasswordsMatch : confirmPasswordInput.setCustomValidity(confirmPasswordMessage); break;
        }
        e.target.reportValidity();
    }
})

function resetValidity() {
    $('#signup-login')[0].setCustomValidity('');
    $('#signup-password')[0].setCustomValidity('');
    $('#signup-confirm-password')[0].setCustomValidity('');
}