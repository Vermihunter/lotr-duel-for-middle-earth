open module lotr {
    requires com.fasterxml.jackson.databind;
    requires org.reflections;
    requires java.desktop;
    requires java.rmi;
    requires org.slf4j;
    requires java.logging;
    requires java.compiler;

    // Model
    exports vermesa.lotr.model.central_board;
    exports vermesa.lotr.model.actions;
    exports vermesa.lotr.model.actions.basic_actions;
    exports vermesa.lotr.model.actions.coin_actions;
    exports vermesa.lotr.model.actions.central_board_actions;
    exports vermesa.lotr.model.actions.chapter_card_actions;
    exports vermesa.lotr.model.actions.race_effect_actions;
    exports vermesa.lotr.model.actions.ring_quest_track_actions;
    exports vermesa.lotr.model.utils;
    exports vermesa.lotr.model.game;
    exports vermesa.lotr.model.moves;
    exports vermesa.lotr.model.chapter_cards;
    exports vermesa.lotr.model.landmark_effects;
    exports vermesa.lotr.model.player;
    exports vermesa.lotr.model.quest_of_the_ring_track;
    exports vermesa.lotr.model.skills;
    exports vermesa.lotr.model.race_effects;

    // AI players
    exports vermesa.lotr.ai_players;

    // Controllers
    exports vermesa.lotr.controllers;

    // View
    exports vermesa.lotr.view.console;
    exports vermesa.lotr.view.console.move_serializers;
    exports vermesa.lotr.view.console.utils;
    exports vermesa.lotr.view.console.state_serializers;
    exports vermesa.lotr.view.console.game_events;
    exports vermesa.lotr.view.console.annotations;
    exports vermesa.lotr.view.console.event_handlers;
    exports vermesa.lotr.view.console.grid;

    // Serialization
    exports vermesa.lotr.serialization;
    exports vermesa.lotr.serialization.json;
    exports vermesa.lotr.serialization.json.players;
}
