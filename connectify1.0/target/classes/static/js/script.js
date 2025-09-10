console.log("Script Loaded");

let currentTheme = getTheme();
applyTheme(currentTheme);

// Function to change the theme
function changeTheme() {
    let theme = getTheme() === "light" ? "dark" : "light";
    setTheme(theme);
    applyTheme(theme);
}

// Function to apply the theme
function applyTheme(theme) {
    document.querySelector('html').classList.remove("light", "dark");
    document.querySelector('html').classList.add(theme);
}

// Function to set theme in local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Function to get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

// Event listener for theme change button
document.getElementById("theme_change_button").addEventListener("click", changeTheme);
