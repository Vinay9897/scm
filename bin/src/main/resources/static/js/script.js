let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
changeTheme();

});


function changeTheme(){

    const oldTheme = currentTheme;

    const changeThemeButton = document.querySelector('#theme_change_button');

    changeThemeButton.addEventListener("click",() =>{

        if(currentTheme  === "dark") {
            currentTheme = "light";
        }
        else if(currentTheme === "light"){
            currentTheme = "dark";
        }

        // update the new theme in localStorage
       changePageTheme(currentTheme,oldTheme);
    });
}

function changePageTheme(currentTheme, oldTheme){
    setTheme(currentTheme);
    // remove the current theme
     console.log(oldTheme);
    document.querySelector('html').classList.remove(oldTheme);

    //set the new current theme
    document.querySelector('html').classList.add(currentTheme);
     console.log(currentTheme);

    // change button text
    document.querySelector('#theme_change_button').querySelector('span').textContent = currentTheme === "light" ? "Dark" : "Light";

}

function setTheme(theme){
    localStorage.setItem("theme",theme);
}

function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light" ;
}