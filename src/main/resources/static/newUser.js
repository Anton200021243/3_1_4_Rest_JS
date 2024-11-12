const form_new = document.forms["formNewUser"];
const roles_new = document.querySelector('#rolesNew').selectedOptions;
form_new.addEventListener("submit", ev => {
    ev.preventDefault();
    let listOfRole = [];
    for (let i = 0; i < roles_new.length; i++) {
        listOfRole.push({
            id: roles_new[i].value,
            username: "ROLE_" + roles_new[i].text
        });
        console.log(listOfRole);
    }
    fetch('/api/admin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: form_new.username.value,
            email: form_new.email.value,
            password: form_new.password.value,
            roles: listOfRole
        })
    }).then(() => {
        form_new.reset();
        allUsers();
        $('#users-table').click();
    });
})