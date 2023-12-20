let happyDrinks = [];
let sadDrinks = [];
let lonelyDrinks = [];
let boredDrinks = [];

function suggestDrink(mood) {
    let drinks;
    switch (mood.toLowerCase()) {
        case "happy":
            drinks = happyDrinks;
            break;
        case "sad":
            drinks = sadDrinks;
            break;
        case "lonely":
            drinks = lonelyDrinks;
            break;
        case "bored":
            drinks = boredDrinks;
            break;
        default:
            return "Invalid mood. Please choose from: happy, sad, lonely, or bored.";
    }

    let selectedDrink = drinks[Math.floor(Math.random() * drinks.length)];
    return `For your ${mood} mood, I suggest a ${selectedDrink}.`;
}

let userMood = prompt("Enter your mood (happy, sad, lonely, or bored): ");
let result = suggestDrink(userMood);
console.log(result);