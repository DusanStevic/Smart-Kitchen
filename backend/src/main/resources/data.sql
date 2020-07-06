-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Ovo su sve imena kolona u tabelama db(Column anotacija u klasama), a ne imena polja u klasama

INSERT INTO authorities (id, role) VALUES (1, 'ROLE_REGISTERED_USER');
INSERT INTO authorities (id, role) VALUES (2, 'ROLE_ADMIN');


-- password is 'user' (bcrypt encoded)
INSERT INTO users (user_type, id, username, password, first_name, last_name, email, enabled,last_password_reset_date, phone_number, first_time, image_url) VALUES ( 'user', 1, 'user', '$2a$10$AIj1/uEYflVWgnovOkb0Uu8FgMYTIdgX.MCm4ZLT9Xvd66h/1UnuC','User_Name', 'User_Lastname', '2020smartkitchen2020+user@gmail.com', true, '2020-04-22 22:22:00', '0641234567', false, 'https://res.cloudinary.com/djxkexzcr/image/upload/v1574108111/zbvvptxlxzzhzomjvp2s.jpg');
-- password is 'admin' (bcrypt encoded)
INSERT INTO users (user_type, id, username, password, first_name, last_name, email, enabled,last_password_reset_date, phone_number, first_time, image_url) VALUES ( 'admin', 2, 'admin', '$2a$10$CDnBIedTKUJbyG3b/qJ2G.Vjx45s9nCZFpPn9id/eIh/siZ81bO3W','Admin_Name', 'Admin_Lastname', '2020smartkitchen2020+admin@gmail.com', true, '2020-04-22 22:22:00', '0647654321', false, 'https://res.cloudinary.com/djxkexzcr/image/upload/v1574108111/zbvvptxlxzzhzomjvp2s.jpg');



INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1); -- registered user has ROLE_REGISTERED_USER
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2); -- adminstrator has ROLE_ADMIN



--ingredients(VEGETABLES)
--VEGETABLES
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (1, 'Tomato', 'Tomato vegetable', 1, true, 'https://res.cloudinary.com/djxkexzcr/image/upload/v1592067980/tomatoes.jpg', 12, 23);

INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (2, 'ingredient2', 'ingredient2', 0, true,null, 2,20);
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (3, 'ingredient3', 'ingredient3', 0, true,null, 3,30);
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (4, 'ingredient4', 'ingredient4', 0, true,null, 4,40);
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (5, 'ingredient5', 'ingredient5', 1, true,null, 5,50);
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (6, 'ingredient6', 'ingredient6', 1, true,null, 6,60);
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (7, 'ingredient7', 'ingredient7', 1, true,null, 7,70);
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (8, 'ingredient8', 'ingredient8', 1, true,null, 8,80);
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (9, 'ingredient1', 'ingredient1', 0, true,null, 1,10);
INSERT INTO `ingredients` (id, name, description, ingredient_type, active, image_url, unit_type, calories_per_unit) VALUES (10, 'ingredient1', 'ingredient1', 0, true,null, 1,10);

--recipes
INSERT INTO `recipes` (id, name, description, recipe_type, active, image_url,  rating_id, difficulty, total_calories, preparation_time) VALUES (1, 'recipe1', 'recipe1', 0,true,null,1,null,null,110);
INSERT INTO `recipes` (id, name, description, recipe_type, active, image_url,  rating_id, difficulty, total_calories, preparation_time) VALUES (2, 'recipe2', 'recipe2', 1,true,null,2,null,null,120);
INSERT INTO `recipes` (id, name, description, recipe_type, active, image_url,  rating_id, difficulty, total_calories, preparation_time) VALUES (3, 'recipe3', 'recipe3', 3,false,null,3,null,null,30);
INSERT INTO `recipes` (id, name, description, recipe_type, active, image_url,  rating_id, difficulty, total_calories, preparation_time) VALUES (4, 'recipe4', 'recipe4', 4,true,null,4,null,null,40);
INSERT INTO `recipes` (id, name, description, recipe_type, active, image_url,  rating_id, difficulty, total_calories, preparation_time) VALUES (5, 'recipe5', 'recipe5', 4,true,null,5,null,null,50);
INSERT INTO `recipes` (id, name, description, recipe_type, active, image_url,  rating_id, difficulty, total_calories, preparation_time) VALUES (6, 'recipe6', 'recipe6', 4,true,null,6,null,null,60);
--directions
INSERT INTO `directions` (id, description, recipe_id) VALUES (1, 'Combine almond milk and rice in a 2-3 quart saucepan, and bring to a boil.', 1);
INSERT INTO `directions` (id, description, recipe_id) VALUES (2, 'Reduce heat and simmer for 1/2 hour with the lid on until the rice is soft.', 1);
INSERT INTO `directions` (id, description, recipe_id) VALUES (3, 'Add sugar, vanilla, almond extract and cinnamon. Stir and serve warm.', 1);
INSERT INTO `directions` (id, description, recipe_id) VALUES (4, 'Refrigerate leftovers within 2 hours.', 1);
INSERT INTO `directions` (id, description, recipe_id) VALUES (8, 'Refrigerate leftovers within 2 hours.', 2);
INSERT INTO `directions` (id, description, recipe_id) VALUES (7, 'Add sugar, vanilla, almond extract and cinnamon. Stir and serve warm.', 2);
INSERT INTO `directions` (id, description, recipe_id) VALUES (6, 'Reduce heat and simmer for 1/2 hour with the lid on until the rice is soft.', 2);
INSERT INTO `directions` (id, description, recipe_id) VALUES (5, 'Combine almond milk and rice in a 2-3 quart saucepan, and bring to a boil.', 2);




--recipe_items
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (1, 1, 1, 10);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (2, 1, 2, 20);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (3, 2, 3, 30);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (4, 2, 4, 40);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (5, 1, 5, 50);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (6, 1, 6, 60);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (7, 2, 7, 70);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (8, 2, 8, 80);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (9, 3, 9, 90);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (10, 4, 10, 100);
INSERT INTO `recipe_items` (id, recipe_id, ingredient_id, amount) VALUES (11, 1, 3, 100);

--ratings
INSERT INTO `ratings` (id, frequency1, frequency2, frequency3, frequency4, frequency5, frequency_like, frequency_dislike) VALUES (1, 33, 29, 40, 124, 252, 75, 10);
INSERT INTO `ratings` (id, frequency1, frequency2, frequency3, frequency4, frequency5, frequency_like, frequency_dislike) VALUES (2, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `ratings` (id, frequency1, frequency2, frequency3, frequency4, frequency5, frequency_like, frequency_dislike) VALUES (3, 33, 0, 40, 124, 252, 75, 0);
INSERT INTO `ratings` (id, frequency1, frequency2, frequency3, frequency4, frequency5, frequency_like, frequency_dislike) VALUES (4, 0, 10, 0, 0, 4, 0, 10);
INSERT INTO `ratings` (id, frequency1, frequency2, frequency3, frequency4, frequency5, frequency_like, frequency_dislike) VALUES (5, 33, 29, 40, 124, 252, 75, 10);
INSERT INTO `ratings` (id, frequency1, frequency2, frequency3, frequency4, frequency5, frequency_like, frequency_dislike) VALUES (6, 0, 10, 0, 0, 0, 0, 10);
        
--fridge_items
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (1, 1, 1, 10);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (2, 1, 2, 20);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (3, 1, 3, 30);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (4, 1, 4, 40);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (5, 1, 5, 50);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (6, 1, 6, 60);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (7, 1, 7, 70);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (8, 1, 8, 80);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (9, 1, 9, 90);
INSERT INTO `fridge_items` (id, user_id, ingredient_id, amount) VALUES (10, 1, 10, 100);