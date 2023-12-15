const arabica = [
{
coffeeId: 1,
name: "Espresso",
FlavorProfiles: "Indulge in the essence of robust richness and unwavering boldness. \nA symphony of deep complexity unfolds with each sip, accompanied by an enduring aftertaste that lingers, \ninviting you to relish the profound character in every moment.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Single or double shot, served in a small cup.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
  
{
coffeeId: 2,
name: "Americano",
FlavorProfiles: "Embark on a journey of simplicity and strength with an Americano—a coffee experience that champions the essence of espresso softened by \nthe embrace of hot water. In each sip of an Americano, discover a melody of strength and simplicity—a coffee symphony where the powerful notes \nof espresso harmonize with the soothing tones of hot water, creating a cup that resonates with both depth and drinkability.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Espresso shots diluted with hot water.",
RecommendedBeans: "Arabica beans"
},
{
coffeeId: 3,
name: "Latte",
FlavorProfiles: "Embark on a journey of indulgence with a Latte—a symphony of flavors that dance gracefully on the palate.\nIn every sip of a Latte, there's an invitation to linger, to savor the intermingling of textures and flavors. It's not just a coffee; \nit's a moment of sensory delight—a journey through the realms of creaminess, balance, and the artistry of espresso and milk in perfect harmony.",
RoastLevel: "Medium roast.",
ServingStyle: "Espresso with a lot of steamed milk and a small amount of foam.",
RecommendedBeans: "Arabica beans"
},
{
coffeeId: 4,
name: "Cappuccino",
FlavorProfiles: "Indulge in the robust harmony of a cappuccino that commands attention with its strong and bold character. A symphony of flavors unfolds as the \nrich essence of espresso dances with the velvety embrace of steamed milk, creating a luxurious and creamy texture that lingers on the palate. \nThe artful integration of foam adds a delightful touch, elevating each sip to a crescendo of satisfaction. Immerse yourself in the distinct coffee flavor,\n a masterful blend that embodies the perfect balance of intensity and smoothness, making this cappuccino a truly captivating sensory experience.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Equal parts of espresso, steamed milk, and foam.",
RecommendedBeans: "Robusta beans"
},
{
coffeeId: 5,
name: "Macchiato",
FlavorProfiles: "Savor the exquisite simplicity of a macchiato—a canvas of bold flavors delicately intertwined. A shot of intense espresso takes center stage, \nits robust notes painting the foundation for this masterpiece. A mere whisper of steamed milk graces the surface, creating a subtle interplay \nthat teases the taste buds with a creamy touch. The result is a symphony of contrasts—intense and gentle, rich and light—unveiling a fleeting \nmoment of perfection in every sip. The macchiato, a sensory delight that captures the essence of espresso in its purest form, leaving a lingering \nimpression that is both memorable and satisfying.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Espresso \"stained\" or marked with a small amount of foam or milk.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 6,
name: "Flat White",
FlavorProfiles: "Embark on a sensory journey with the flat white, a creation that marries the robust allure of espresso with the silkiness of microfoam. The bold notes \nof the espresso take center stage, commanding attention with their rich complexity. As you take that first sip, the velvety microfoam introduces a \ncreamy dimension, tempering the intensity with a subtle sweetness. The result is a harmonious dance of flavors—strong yet smooth, \neach element complementing the other in a symphony of coffee perfection.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Similar to a latte but with a higher coffee-to-milk ratio and a thin layer\nof microfoam.",
RecommendedBeans: "Arabica beans"
},
{
coffeeId: 7,
name: "Mocha",
FlavorProfiles: "Savoring a mocha is akin to embarking on a sensory escapade, where the marriage of coffee and chocolate creates a delicious narrative. \nIt's an invitation to experience the symphony of flavors—a rendezvous of espresso's intensity and chocolate's velvety embrace, \nculminating in a cup of pure, liquid luxury.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Espresso with steamed milk, chocolate syrup, and whipped cream.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 8,
name: "Cortado",
FlavorProfiles: "As you take that initial sip, experience the seamless fusion of flavors—the robust espresso asserting itself with strength, \nwhile the velvety steamed milk tempers the intensity, creating a remarkably smooth and well-rounded profile. The cortado, a celebration of contrasts, \nshowcases the marriage of rich coffee essence and the comforting embrace of milk in perfect equilibrium.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Equal parts espresso and warm milk.",
RecommendedBeans: "Arabica beans"
},
{
coffeeId: 9,
name: "Drip Brewed Coffee",
FlavorProfiles: "Embarking on a journey of subtlety and refinement, drip-brewed coffee unfolds like a carefully composed symphony. \nThe process, reminiscent of a gentle rain, allows each droplet of water to caress the coffee grounds, \ncoaxing out a nuanced richness.\n",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Coffee brewed by dripping boiling water over ground coffee.\n",
RecommendedBeans: "Arabica beans"
},
{
coffeeId: 10,
name: "Red Eye",
FlavorProfiles: "Picture a sunrise in a cup as you embrace the red eye. This concoction combines the robust allure of freshly brewed drip coffee with a shot of espresso, \ncreating a beverage that's as vibrant as it is powerful. The initial encounter brings forth the rich, earthy tones of the brewed coffee, awakening your \nsenses. Then, like a burst of energy, the espresso joins the symphony, amplifying the depth and adding a layer of complexity. The red eye is an \nexperience that propels you into the day with a harmonious blend of boldness and depth, a perfect companion for those seeking an extra kick in their \ncoffee journey.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Drip coffee with a shot of espresso.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 11,
name: "Black Eye",
FlavorProfiles: "Take the intensity up a notch with the black eye—a caffeine powerhouse that marries the strength of two espresso shots with the robustness of \ndrip-brewed coffee. This bold creation is a double shot of espresso seamlessly integrated with the deep, aromatic notes of the brewed coffee. \nThe result is a beverage that commands attention, unleashing a tidal wave of rich, full-bodied flavors. Each sip is a dynamic dance between the \nconcentrated essence of espresso and the grounded richness of the drip-brewed coffee, delivering an experience that's as invigorating as it is satisfying. \nThe black eye is a bold proclamation of coffee's unbridled strength, perfect for those moments when you need a robust pick-me-up.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Drip coffee with two shots of espresso.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 12,
name: "Long Black ",
FlavorProfiles: "the long black unfolds on the palate like a finely composed melody. The robust and intense notes of the espresso take center stage, accompanied by a \nnuanced backdrop of warmth from the added water. The contrast between strength and subtlety creates a harmonious balance that lingers \non the taste buds.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Similar to an Americano, but with the espresso added to hot water.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 13,
name: "Doppio",
FlavorProfiles: "Step into the world of doppio, a coffee experience that is as bold as it is straightforward. This double shot of espresso, rich and concentrated, delivers an \nintense and robust flavor profile. With its minimalistic approach, the doppio invites you to embrace the pure essence of coffee. Each sip is a symphony of \ndeep, complex notes, a celebration of the coffee bean's intricate flavors. The doppio is a statement—a declaration of coffee's unadulterated strength and \na moment of pure, concentrated pleasure.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Double shot of Espresso",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 14,
name: "Breve",
FlavorProfiles: "Indulge in the creamy richness of a breve, a luscious blend of espresso and half-and-half. This luxurious concoction introduces a velvety texture to the \nbold character of the espresso, creating a harmonious union of strength and indulgence. The breve unfolds on the palate with a delightful dance of \ncontrasts—the robust intensity of the espresso softened by the smooth, decadent embrace of the half-and-half. It's a sip of comfort, a moment of \nindulgence that tantalizes the taste buds with its creamy allure.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Espresso with steamed half-and-half instead of milk.",
RecommendedBeans: "Arabica beans."
},
{
coffeeId: 15,
name: "Vienna",
FlavorProfiles: "Transport yourself to the elegant coffeehouses of Vienna with a sip of Vienna coffee. This classic preparation involves a shot of espresso generously \ncrowned with whipped cream. The result is a symphony of contrasts—the bold, concentrated flavors of the espresso harmonizing with the light, airy \nsweetness of the whipped cream. The Vienna coffee is a decadent treat that invites you to savor the rich heritage of European coffee culture. Each \nsip is a journey through time, a taste of tradition, and a celebration of the artistry that coffee can embody.",
RoastLevel: "Dark roast.",
ServingStyle: "Espresso with whipped cream.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 16,
name: "Caramel Macchiato",
FlavorProfiles: "Caramel Macchiato, a velvety espresso-based delight, combines the intense richness of espresso with the smooth sweetness of caramel syrup, \ngently layered with steamed milk and crowned with a delicate foam topping. This beloved coffee creation boasts a perfect harmony \nbetween the bold espresso and the indulgent caramel, creating a delightful contrast that tantalizes the palate. The addition of steamed \nmilk adds a creamy texture, balancing the robust coffee flavors with a subtle sweetness, while the foam provides a luxurious finish.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Espresso with steamed milk and vanilla syrup, topped with caramel.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 17,
name: "Gingerbread Latte",
FlavorProfiles: "\nGingerbread Latte, an inviting espresso-based concoction, blends the robustness of espresso with the warm, comforting essence of gingerbread-flavored \nsyrup, harmoniously mixed with steamed milk and adorned with a generous dollop of whipped cream. This festive delight captures the essence \nof the holidays,intertwining the bold espresso with the nostalgic, spiced notes of gingerbread. The addition of steamed milk contributes to a \nvelvety smoothness, enhancing the fusion of flavors, while the luscious whipped cream crowns this comforting beverage, creating a delightful \nsymphony of flavors that evokes a cozy and indulgent seasonal experience.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Latte with gingerbread flavoring.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 18,
name: "Peppermint Mocha",
FlavorProfiles: "Peppermint Mocha blends robust espresso with refreshing peppermint syrup, decadent chocolate, steamed milk, and a dollop of whipped cream \nfor a luscious and festive treat. This seasonal favorite marries the intense coffee notes with the cool minty freshness and rich chocolate, \ndelivering a harmonious balance of flavors",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Mocha with peppermint flavoring.",
RecommendedBeans: "A blend of Arabica and Robusta beans "
},
{
coffeeId: 19,
name: "Chai Latte",
FlavorProfiles: "Embark on a sensory journey with the Chai Latte, a fusion of bold spices and velvety steamed milk. As you lift the cup, the fragrant aroma of chai \nspices—cinnamon, cardamom, cloves, and more—whisks you away to distant lands. The robust black tea base provides a canvas for the spices \nto unfold, creating a tapestry of warm, invigorating flavors. The steamed milk adds a luxurious creaminess, tempering the boldness of the spices \nand inviting you to savor a moment of comfort. Each sip is a harmonious blend of the exotic and familiar, a chai latte experience that transcends the \nordinary and transports you to a realm of aromatic delight.",
RoastLevel: "Medium roast.",
ServingStyle: "Steamed milk with chai tea concentrate.",
RecommendedBeans: "Arabica beans."
},
{
coffeeId: 20,
name: "Pour-over Coffee",
FlavorProfiles: "Experience the artistry of coffee with a Pour-Over that transcends the ordinary. As hot water meticulously dances over freshly ground coffee grounds, \na fragrant aroma fills the air, promising a sensory journey. The pour-over method, a slow and deliberate process, allows the coffee's essence to unfold \ngradually. The result is a cup that boasts the nuanced intricacies of the beans—bright and acidic notes mingling with deep, rich undertones. Each sip is  a revelation, a symphony of flavors that captures the true essence of the coffee bean. The pour-over is more than a brewing method; it's a ritual, an immersive experience that invites you to appreciate coffee in its purest, most unfiltered form.",
RoastLevel: "Light to medium roast",
ServingStyle: "Hot water poured over ground coffee in a filter.",
RecommendedBeans: "Arabica beans"
},
{
coffeeId: 21,
name: "Irish Coffee",
FlavorProfiles: "Elevate your coffee experience with the spirited charm of Irish Coffee. This classic libation seamlessly blends the robust intensity of hot brewed coffee \nwith the smooth richness of Irish whiskey, creating a symphony of warmth and depth. The first sip unfolds with the bold character of the coffee, which \nserves as the foundation for the dance of flavors to come. As the velvety whiskey weaves its way through, a subtle sweetness emerges, \ncomplementing the coffee's bitterness. Topped with a crown of whipped cream, the Irish Coffee is a luxurious indulgence that intertwines the warmth \nof coffee with the spirited embrace of Irish tradition. It's not just a drink; it's a celebration—a toast to the perfect fusion of caffeine and camaraderie.\n",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Hot coffee with Irish whiskey and sugar, topped with cream.",
RecommendedBeans: "A blend of Arabica and Robusta beans ",
BrewingMethod: "- Brew a strong cup of coffee.\n- Add brown sugar to the hot coffee and stir until dissolved.\n- Add Irish whiskey to the coffee and stir.\n- Gently float a layer of heavy cream on top by pouring it over the back of a spoon.\n- The cream should rest on the surface of the coffee."
},
{
coffeeId: 22,
name: "Turkish Coffee",
FlavorProfiles: "Bold and commanding, the aroma dances in the air, enticing your senses before the first sip.\nA symphony of robust flavors that make a powerful statement on your palate.\nThe strength is not just in intensity but in the layers of complexity, revealing nuances with each sip.\nAromatic notes swirl and intertwine, creating an aromatic experience that precedes and accompanies the robust taste.\n",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Finely ground coffee beans boiled with sugar and cardamom.",
RecommendedBeans: "Arabica beans",
BrewingMethod: "- Add finely ground coffee, water, and sugar (if desired) to the cezve.\n- Stir the mixture well and place it on low heat.\n- Allow the coffee to heat without stirring until it starts to foam.\n- Just before it boils, remove from heat.\n- Pour the unfiltered coffee into a small cup, grounds and all.\n- Let the grounds settle before sipping."
},
{
coffeeId: 23,
name: "Cold Brew",
FlavorProfiles: "Embarking on a journey of unparalleled smoothness, cold brew unveils a velvety tapestry on the palate.\nWith a deliberate avoidance of sharp acidity, each sip glides effortlessly, offering a serene and gentle experience.\nThe absence of the typical bite found in hot-brewed counterparts allows for a tranquil exploration of the coffee's intricate nuances.\n\"Cold brew tends to have a milder and less bitter taste compared to traditional hot-brewed coffee.\nThe slow extraction process emphasizes the natural sweetness of the coffee.\"",
RoastLevel: "Coarse Grind, Medium to dark roast.",
ServingStyle: "Coffee brewed with cold water over an extended period"
},
{
coffeeId: 24,
name: "Iced Coffee",
FlavorProfiles: "Iced coffee, a mirror reflecting the essence of its hot-brewed roots, retains the familiar symphony of flavors.\nEchoing the heart and soul of traditional coffee, it preserves the signature notes that aficionados hold dear.\nIn every sip, the legacy of hot-brewed coffee unfolds, inviting you into a realm of familiarity and comforting echoes.\nCan have more acidity and bitterness compared to cold brew.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Chilled coffee served over ice"
},
{
coffeeId: 25,
name: "Affogato",
FlavorProfiles: "The affogato, a sweet sonnet in the world of coffee, beckons with a dessert-like allure that borders on the divine.\nIt's not just coffee; it's a decadent ceremony, an invitation to indulge in the symphony of contrasting temperatures and textures.\nWith each spoonful, a crescendo of flavors unfolds, transforming the ordinary into an extraordinary experience.\nVanilla ice cream or gelato \"drowned\" in a shot of hot espresso.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Espresso poured over a scoop of vanilla ice cream"
},
{
coffeeId: 26,
name: "Café au Lait",
FlavorProfiles: "Step into the comforting embrace of a Café au Lait—a simple yet satisfying melody of brewed coffee and warm milk.\nIn each sip of a Café au Lait, discover the charm of a classic pairing—a timeless blend that resonates with the essence of comfort and \nuncomplicated elegance.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Equal parts brewed coffee and steamed milk"
},
{
coffeeId: 27,
name: "Frappuccino",
FlavorProfiles: "Experience the bliss of Starbucks' Frappuccino, a symphony of iced, blended coffee drinks crowned with whipped cream and infused with delightful syrups. Think of it as a coffee milkshake, a refreshing treat where the flavor possibilities are as boundless as your imagination. Blending coffee or espresso, milk, and ice, the Frappuccino emerges from the blender with a silky, creamy texture that invites customization. Add syrups and whipped cream to tailor the flavor to your liking. A popular choice for cool coffee enthusiasts, Frappuccinos offer a refreshing escape with a lower caffeine content, ensuring each sip is a moment of pure, cool indulgence.",
RoastLevel: "N\/A (usually made with espresso or coffee extract).",
ServingStyle: "Blended coffee beverage with ice, milk, and flavorings"
},
{
coffeeId: 28,
name: "Con panna",
FlavorProfiles: "Indulge in Con Panna, a sweet serenade of espresso and whipped cream. A dance of bitterness and sweetness, where a velvety dollop enhances the rich, nutty notes of coffee. This dessert-like treat is a delightful symphony, a perfect balance without the need for added sugar. Dive into the simple elegance of 'espresso with cream,' a poetic pairing that turns every sip into a sweet, creamy escape.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Espresso topped with whipped cream"
},
{
coffeeId: 29,
name: "Ristretto",
FlavorProfiles: "Immerse yourself in the allure of the \"restricted\" - a Ristretto, a shot of espresso with a flavor as sweet as molasses and a richness that dances on the palate. Unlike its bolder espresso counterpart, the Ristretto, meaning \"short shots\" in Italian, boasts a unique charm. Its secret lies in a precise, shorter extraction time and less water, crafting a potion of unparalleled boldness and intensity. Over a thousand compounds interweave to orchestrate a symphony of aroma, ensuring a sweeter taste that lingers delicately, making each sip a poetic journey of refined coffee indulgence.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "A very short shot of espresso"
},
{
coffeeId: 30,
name: "Lungo",
FlavorProfiles: "Immerse yourself in the artistry of the \"long\" - the Lungo, where milder tones embrace pronounced hints of chocolate, nuts, and caramel. A dance of flavors unfolds, offering a bolder bitterness that distinguishes this coffee experience. As an extended sibling of the espresso family, Lungo takes its time, delivering a larger, less concentrated essence while preserving the unique richness we associate with espresso. Italian for \"long,\" Lungo indulges in a ritual of one shot of espresso, elegantly stretched with twice as much hot water. The result is a slightly larger serving, akin to a doppio, yet harboring an intensified caffeine kick. A symphony of strength and bitterness, Lungo offers a prolonged moment of coffee artistry, featuring a thinner crema but an exquisite depth that lingers on the palate.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "A long shot of espresso"
},
{
coffeeId: 31,
name: "Galao",
FlavorProfiles: "Embrace the allure of Portugal with Galao, a morning embrace of one part espresso and three parts frothy milk. This cherished blend paints a canvas of balanced flavors, where the robust notes of roasted espresso harmonize with the creamy whispers of frothed milk. With a higher coffee-to-milk ratio, Galao promises a sip of indulgence, a gentle embrace that transports you to the sunlit cafes of Portugal. Roasted to perfection, each drop echoes the artful dance between rich espresso and velvety milk, creating a symphony of taste that captures the essence of this beloved Portuguese delight.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Portuguese drink with one-fourth coffee and three-fourths foamed milk"
},
{
coffeeId: 32,
name: "Mazagran",
FlavorProfiles: "Discover the extraordinary world of Mazagran, affectionately known as \"lemon coffee.\" This enigmatic brew unfolds a tapestry of flavors, where the bold, dark notes of espresso dance in harmony with the vibrant citrus swirls. It's a taste that defies expectation, a complexity you must experience firsthand. The classic Mazagran, a coffee lemonade, is a blend of espresso, ice, a hint of water, and the zesty touch of fresh lemon juice. This iced coffee beverage transcends tradition, offering a refreshing interplay of strong black coffee over ice, sometimes sweetened, and occasionally adorned with the bright essence of lemon juice. Mazagran is not just a drink; it's a journey into the realm of exquisite and unexpected taste.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Cold coffee sweetened with sugar and mixed with lemon"
},
{
coffeeId: 33,
name: "Bulletproof",
FlavorProfiles: "Elevate your coffee experience with the sophistication of Bulletproof—a blend of premium coffee beans, grass-fed butter, and medium-chain triglyceride (MCT) oil, crafting a beverage that is both creamy and rich. This unique combination of healthy fats and caffeine is renowned for enhancing energy levels, fostering mental clarity, and sustaining unwavering focus. The taste of Bulletproof coffee is a symphony of smoothness and velvetiness, adorned with a lush, buttery flavor. The inclusion of grass-fed butter imparts a delightful creaminess, while the MCT oil introduces a subtle, nutty undertone. With Original Bulletproof coffee beans offering a brighter flavor profile, this concoction adds high-quality fats—not sugar—to your morning cup, ensuring sustained energy levels for a sharper, stronger version of yourself. Indulge in the artistry of Bulletproof, where each sip is a journey into the realm of indulgence and vitality.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Coffee blended with butter and coconut oil"
},
{
coffeeId: 34,
name: "Mexican Coffee",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Coffee with cinnamon and sometimes chocolate"
},
{
coffeeId: 35,
name: "Kopi Luwak",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Coffee made using beans eaten and excreted by civets"
},
{
coffeeId: 36,
name: "Cuban Latte",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Strong espresso sweetened with demerara sugar during the brewing process."
},
{
coffeeId: 37,
name: "Cascara Latte",
RoastLevel: "N\/A (made from dried coffee cherry husks).",
ServingStyle: "Made from the dried skins of coffee cherries, brewed like tea, and combined \nwith steamed milk."
},
{
coffeeId: 38,
name: "Coconut Coffee",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Coffee made with coconut milk or coconut water for a tropical twist."
},
{
coffeeId: 39,
name: "Maple Pecan Latte",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Latte flavored with maple syrup and pecan extracts."
},
{
coffeeId: 40,
name: "Cardamom Coffee",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Coffee brewed with the addition of cardamom pods for a fragrant and spiced flavor."
},
{
coffeeId: 41,
name: "Honey Lavender Latte",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Latte infused with honey and lavender syrup for a floral sweetness."
},
{
coffeeId: 42,
name: "Café de Olla",
FlavorProfiles: "Café de Olla, a traditional Mexican coffee, presents a remarkable harmony between the depth of black coffee and the comforting warmth of cinnamon spice and the gentle sweetness of piloncillo brown sugar. Brewed in a clay pot, this coffee offers a distinctive and captivating flavor profile with the natural sweetness of piloncillo and the rich aroma of cinnamon, creating a sensory experience deeply intertwined with a rich cultural heritage, pleasing to the senses. The unique blend of bold coffee and subtle spice creates a memorable journey, delving into the soul and flavors of Mexico in every warm sip.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Mexican coffee made with cinnamon and piloncillo (unrefined cane sugar)."
},
{
coffeeId: 43,
name: "White Mocha",
FlavorProfiles: "Dive into the elegance of our White Mocha—a velvety symphony where espresso dances with white chocolate syrup, steamed milk, and a crown of whipped cream. A sweet, creamy dream with subtle chocolate whispers, it's a latte-style embrace of indulgence. Crafted with our signature white chocolate elixir, this variant enchants the traditional mocha, introducing a sweeter, smoother dimension. Whether at a coffee shop or in your haven at home, the White Mocha is a cup of pure bliss, where every sip is poetry in coffee form.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Similar to a regular mocha but made with white chocolate instead of milk or dark chocolate."
},
{
coffeeId: 44,
name: "Raspberry Mocha",
FlavorProfiles: "Immerse yourself in the symphony of rich chocolate and vibrant raspberry flavors for a truly delightful experience. Our dark roasted coffee forms a robust foundation perfectly complemented by the sweet and tart embrace of raspberry syrup. This harmonious blend of raspberries and mocha promises to infuse love into your life, all without the guilt of calories. Indulge in the poetry of Raspberry Mocha, where each sip is a celebration of flavors that dance on the palate.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Mocha with the addition of raspberry flavor, providing a fruity twist to the chocolatey goodness."
},
{
coffeeId: 45,
name: "Coconut Mocca",
FlavorProfiles: " A dance of sweet coconut and rich chocolate, our seasonal blend is a symphony in every sip. Crafted with plant-based goodness, it's keto, vegan, low in carbs—energizing your moments with the beloved taste of Super Coffee. Dark chocolate and coconut flakes unite, making Mocha Coconut our sweet touch to your daily cup. Indulge in the poetry of flavors, a sip of pure delight.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Mocha with coconut milk or coconut-flavored syrup, offering a tropical and creamy experience."
},
{
coffeeId: 46,
name: "Honey Almond Latte",
FlavorProfiles: "Double the almond goodness in every sip—this latte blends creamy almond butter, real honey, strong coffee, and a hint of cinnamon and nutmeg. It's a subtly sweet and flavorful pick-me-up, not overly dessert-like but a perfect morning indulgence. Bitter coffee finds balance with the right touch of honey and the richness of almonds—a duo like no other. Crafted with fresh, local honey and frothy almond milk, our Honey Almond Latte is the warm and toasty embrace your day craves.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Latte with honey and almond flavors, creating a sweet and nutty combination."
},
{
coffeeId: 47,
name: "Café Bombón",
FlavorProfiles: "A sweet symphony of espresso and condensed milk, the Café Bombón transcends borders. Hailing from Valencia, Spain, this dessert-like indulgence blends the richness of 'dulce de leche' with the bold essence of espresso. The condensed milk, with its sugary allure, creates a caramel-like texture, harmonizing with the robust bitterness of coffee. Silky and satisfying, this espresso treat is a journey for your sweet tooth, tempting you to consider travel just for the love of coffee. A classic recipe enriched with vanilla extract and cocoa powder, optionally crowned with whipping cream, it's the perfect treat to savor and transport your taste buds.",
RoastLevel: "Dark roast.",
ServingStyle: "Espresso served with sweetened condensed milk"
},
{
coffeeId: 48,
name: "Vanilla Soy Latte",
FlavorProfiles: "Indulge in the velvety allure of our creamy, sweet Vanilla Soy Latte—a frothy, lightly sweetened, and vanilla-infused delight. Crafted with soy milk, this dairy-free treat is not only super creamy but also boasts added richness for that perfect froth. With a higher fat content and protein, soy milk adds a luxurious touch to this delicately flavored latte. Sip and savor the essence of a soy-based, dairy-free indulgence, where each moment is a silky symphony of vanilla-infused delight.",
RoastLevel: "Medium roast.",
ServingStyle: "Latte made with soy milk and infused with vanilla syrup, catering to those who prefer a \nnon-dairy and subtly sweet option."
},
{
coffeeId: 49,
name: "Irish Cream Flavored Coffee",
FlavorProfiles: "Immerse yourself in the velvety richness of Irish Cream—a smooth blend featuring sweet creme and vanilla notes, elevated with the essence of Irish Cream whiskey. This remarkably mellow and warming concoction, enhanced by our premium roasted coffee, delivers the smoky aroma and superb taste of chocolate, vanilla, and caramel. It's a spirited experience without the alcohol, offering a lively sweet taste in every sip of our 100% Arabica roasted coffee. This classic brew harmonizes the buttery blend of Irish Whiskey and cream with a delightful almond and hazelnut aroma. Suitable for various water-soluble applications, it's formulated to hit all the right notes—smooth, creamy, with a hint of coffee brewing.",
RoastLevel: "Medium to dark roast.",
ServingStyle: "Typically served in a mug with hot brewed coffee and a generous splash of Irish cream liqueur.",
RecommendedBeans: "A blend of arabica and robusta."
},
{
coffeeId: 50,
name: "Tiramisu Latte",
FlavorProfiles: "Delight in the exquisite fusion of our Tiramisu Latte—crafted with steamed milk, mascarpone syrup, and espresso, adorned with cocoa espresso powder. This coffee indulgence beautifully combines the flavors of tiramisu and coffee, delivering a not-too-sweet, perfectly textured experience enriched with cocoa powder and mascarpone cream. Sip and savor the magic of Tiramisu Latte, where each cup is a journey into decadence.",
RoastLevel: "Medium roast.",
ServingStyle: " Served as a latte with flavors reminiscent of the classic Italian tiramisu dessert, often topped \nwith cocoa powder.",
RecommendedBeans: "Arabica beans"
}
]

module.exports = arabica;
