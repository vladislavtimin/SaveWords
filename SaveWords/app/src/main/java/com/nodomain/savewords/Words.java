package com.nodomain.savewords;


import java.util.ArrayList;
import java.util.List;


public interface Words {

    List<Word> food = new ArrayList<Word>(){{
        add(new Word("Миндальное", "Almond", null, null, R.raw.food_almond, R.drawable.food_almond));
        add(new Word("Яблоко", "Apple", null, null, R.raw.food_apple, R.drawable.food_apple));
        add(new Word("Корзина", "Basket", null, null, R.raw.food_basket, R.drawable.food_basket));
        add(new Word("Говядина", "Beef", null, null, R.raw.food_beef, R.drawable.food_beef));
        add(new Word("Свекольный", "Beet", null, null, R.raw.food_beet, R.drawable.food_beet));
        add(new Word("Печенье", "Biscuits", null, null, R.raw.food_biscuits, R.drawable.food_biscuits));
        add(new Word("Хлеб", "Bread", null, null, R.raw.food_bread, R.drawable.food_bread));
    }};

    List<Word> games = new ArrayList<Word>() {{
        add(new Word("Шахматы", "Chess", null, null, R.raw.games_chess, R.drawable.games_chess));
        add(new Word("Шахматная доска", "Chessboard", null, null, R.raw.games_chessboard, R.drawable.games_chessboard));
        add(new Word("Кости", "Dice", null, null, R.raw.games_dice, R.drawable.games_dice));
        add(new Word("Домино", "Dominoes", null, null, R.raw.games_dominoes, R.drawable.games_dominoes));
        add(new Word("Шашки", "Draughts", null, null, R.raw.games_draughts, R.drawable.games_draughts));
        add(new Word("Джойстик", "Joystick", null, null, R.raw.games_joystick, R.drawable.games_joystick));
        add(new Word("Пользователь", "User", null, null, R.raw.games_user, R.drawable.games_user));
    }};

    List<Word> house = new ArrayList<Word>() {{
        add(new Word("Ванная комната", "Bathroom", null, null, R.raw.house_bathroom, R.drawable.house_bathroom));
        add(new Word("Спальня", "Bedroom", null, null, R.raw.house_bedroom, R.drawable.house_bedroom));
        add(new Word("Квартира", "Flat", null, null, R.raw.house_flat, R.drawable.house_flat));
        add(new Word("Дом", "House", null, null, R.raw.house_house, R.drawable.house_house));
        add(new Word("Кухня", "Kitchen", null, null, R.raw.house_kitchen, R.drawable.house_kitchen));
        add(new Word("Зеркало", "Mirror", null, null, R.raw.house_mirror, R.drawable.house_mirror));
        add(new Word("Туалет", "Toilet", null, null, R.raw.house_toilet, R.drawable.house_toilet));
    }};

    List<Word> movies = new ArrayList<Word>() {{
        add(new Word("Действие", "Action", null, null, R.raw.movie_action, R.drawable.movie_action));
        add(new Word("Кино", "Cinema", null, null, R.raw.movie_cinema, R.drawable.movie_cinema));
        add(new Word("Комедия", "Comedy", null, null, R.raw.movie_comedy, R.drawable.movie_comedy));
        add(new Word("Сосед", "Neighbor", null, null, R.raw.movie_neighbor, R.drawable.movie_neighbor));
        add(new Word("Место", "Seat", null, null, R.raw.movie_seat, R.drawable.movie_seat));
        add(new Word("Авиабилет", "Ticket", null, null, R.raw.movie_ticket, R.drawable.movie_ticket));
        add(new Word("Касса", "Ticket office", null, null, R.raw.movie_ticket_office, R.drawable.movie_ticket_office));
    }};

    List<Word> music = new ArrayList<Word>() {{
        add(new Word("Хор", "Chorus", null, null, R.raw.music_chorus, R.drawable.music_chorus));
        add(new Word("Композитор", "Composer", null, null, R.raw.music_composer, R.drawable.music_composer));
        add(new Word("Дирижер", "Conductor", null, null, R.raw.music_conductor, R.drawable.music_conductor));
        add(new Word("Барабан", "Drum", null, null, R.raw.music_drum, R.drawable.music_drum));
        add(new Word("Флейта", "Flute", null, null, R.raw.music_flute, R.drawable.music_flute));
        add(new Word("Гитара", "Guitar", null, null, R.raw.music_guitar, R.drawable.music_guitar));
        add(new Word("Примечание", "Note", null, null, R.raw.music_note, R.drawable.music_note));
    }};

    List<Word> nature = new ArrayList<Word>() {{
        add(new Word("Земля", "Earth", null, null, R.raw.nature_earth, R.drawable.nature_earth));
        add(new Word("Трава", "Grass", null, null, R.raw.nature_grass, R.drawable.nature_grass));
        add(new Word("Дождь", "Rain", null, null, R.raw.nature_rain, R.drawable.nature_rain));
        add(new Word("Солнце", "Sun", null, null, R.raw.nature_sun, R.drawable.nature_sun));
        add(new Word("Дерево", "Tree", null, null, R.raw.nature_tree, R.drawable.nature_tree));
        add(new Word("Воды", "Water", null, null, R.raw.nature_water, R.drawable.nature_water));
        add(new Word("Ветер", "Wind", null, null, R.raw.nature_wind, R.drawable.nature_wind));
    }};

    List<Word> pets = new ArrayList<Word>() {{
        add(new Word("Кошка", "Cat", null, null, R.raw.pets_cat, R.drawable.pets_cat));
        add(new Word("Курица", "Chicken", null, null, R.raw.pets_chicken, R.drawable.pets_chicken));
        add(new Word("Собака", "Dog", null, null, R.raw.pets_dog, R.drawable.pets_dog));
        add(new Word("Золотая рыбка", "Goldfish", null, null, R.raw.pets_goldfish, R.drawable.pets_goldfish));
        add(new Word("Хомячок", "Hamster", null, null, R.raw.pets_hamster, R.drawable.pets_hamster));
        add(new Word("Попугай", "Parrot", null, null, R.raw.pets_parrot, R.drawable.pets_parrot));
        add(new Word("Кролик", "Rabbit", null, null, R.raw.pets_rabbit, R.drawable.pets_rabbit));
    }};

    List<Word> studying = new ArrayList<Word>() {{
        add(new Word("Класс", "Class", null, null, R.raw.studying_class, R.drawable.studying_class));
        add(new Word("Образование", "Education", null, null, R.raw.studying_education, R.drawable.studying_education));
        add(new Word("Экзамен", "Exam", null, null, R.raw.studying_exam, R.drawable.studying_exam));
        add(new Word("Школа", "School", null, null, R.raw.studying_school, R.drawable.studying_school));
        add(new Word("Студент", "Student", null, null, R.raw.studying_student, R.drawable.studying_student));
        add(new Word(" Учитель", " Teacher", null, null, R.raw.studying_teacher, R.drawable.studying_teacher));
        add(new Word("Университет", "University", null, null, R.raw.studying_university, R.drawable.studying_university));
    }};

    List<Word> transport = new ArrayList<Word>() {{
        add(new Word("Самолет", "Aircraft", null, null, R.raw.transport_aircraft, R.drawable.transport_aircraft));
        add(new Word("Автобус", "Bus", null, null, R.raw.transport_bus, R.drawable.transport_bus));
        add(new Word("Велосипед", "Bicycle", null, null, R.raw.transport_bicycle, R.drawable.transport_bicycle));
        add(new Word("Автомобиль", "Car", null, null, R.raw.transport_car, R.drawable.transport_car));
        add(new Word("Такси", "Taxi", null, null, R.raw.transport_taxi, R.drawable.transport_taxi));
        add(new Word("Поезд", "Train", null, null, R.raw.transport_train, R.drawable.transport_train));
        add(new Word("Трамвай", "Tram", null, null, R.raw.transport_tram, R.drawable.transport_tram));
    }};

    List<Word> work = new ArrayList<Word>() {{
        add(new Word("Строитель", "Builder", null, null, R.raw.work_builder, R.drawable.work_builder));
        add(new Word("Кондитер", "Confectioner", null, null, R.raw.work_confectioner, R.drawable.work_confectioner));
        add(new Word("Повар", "Cook", null, null, R.raw.work_cook, R.drawable.work_cook));
        add(new Word("Врач", "Doctor", null, null, R.raw.work_doctor, R.drawable.work_doctor));
        add(new Word("Медсестра", "Nurse", null, null, R.raw.work_nurse, R.drawable.work_nurse));
        add(new Word("Программист", "Programmer", null, null, R.raw.work_programmer, R.drawable.work_programmer));
        add(new Word("Резюме", "Summary", null, null, R.raw.work_summary, R.drawable.work_summary));
    }};

    List<Word> added = new ArrayList<>();

    List<Word> allWords = new ArrayList<Word>() {{
        addAll(food);
        addAll(games);
        addAll(house);
        addAll(movies);
        addAll(music);
        addAll(nature);
        addAll(pets);
        addAll(studying);
        addAll(transport);
        addAll(work);
        addAll(added);
    }};

    List<Category> categories = new ArrayList<Category>() {{
        add(new Category(R.drawable.food, "Еда", food));
        add(new Category(R.drawable.games, "Игра", games));
        add(new Category(R.drawable.house, "Дом", house));
        add(new Category(R.drawable.movies, "Фильмы", movies));
        add(new Category(R.drawable.music, "Музыка", music));
        add(new Category(R.drawable.nature, "Природа", nature));
        add(new Category(R.drawable.pets, "Питомцы", pets));
        add(new Category(R.drawable.studying, "Учёба", studying));
        add(new Category(R.drawable.transport, "Транспорт", transport));
        add(new Category(R.drawable.work, "Работа", work));
        add(new Category(R.drawable.added, "Добавленные слова", added));
    }};
}
