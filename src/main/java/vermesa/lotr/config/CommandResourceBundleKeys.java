package vermesa.lotr.config;

/**
 * Represents Bundle keys as constants
 */
public class CommandResourceBundleKeys {

    // Resource bundle name
    public static final String BUNDLE_NAME = "commands";

    // Prefixes
    private static final String HELP_MESSAGES_PREFIX = "help_messages";
    private static final String NAME_PREFIX = "name";

    // Start command configuration
    private static final String START_COMMAND_NAME = "start";
    public static final String START_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + START_COMMAND_NAME;
    public static final String START_NAME = NAME_PREFIX + "." + START_COMMAND_NAME;

    // List command configuration
    private static final String LIST_COMMAND_NAME = "list_commands";
    public static final String LIST_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + LIST_COMMAND_NAME;
    public static final String LIST_NAME = NAME_PREFIX + "." + LIST_COMMAND_NAME;

    // Quit command configuration
    private static final String QUIT_COMMAND_NAME = "quit";
    public static final String QUIT_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + QUIT_COMMAND_NAME;
    public static final String QUIT_NAME = NAME_PREFIX + "." + QUIT_COMMAND_NAME;

    // List available moves command configuration
    private static final String LIST_AVAILABLE_MOVES_COMMAND_NAME = "list_moves";
    public static final String LIST_AVAILABLE_MOVES_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + LIST_AVAILABLE_MOVES_COMMAND_NAME;
    public static final String LIST_AVAILABLE_MOVES_NAME = NAME_PREFIX + "." + LIST_AVAILABLE_MOVES_COMMAND_NAME;
    
    // Show game state command configuration
    private static final String DISPLAY_GAME_STATE_COMMAND_NAME = "state";
    public static final String DISPLAY_GAME_STATE_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + DISPLAY_GAME_STATE_COMMAND_NAME;
    public static final String DISPLAY_GAME_STATE_NAME = NAME_PREFIX + "." + DISPLAY_GAME_STATE_COMMAND_NAME;


    // Create a lobby over a network configuration
    private static final String CREATE_LOBBY_COMMAND_NAME = "createLobby";
    public static final String CREATE_LOBBY_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + CREATE_LOBBY_COMMAND_NAME;
    public static final String CREATE_LOBBY_STATE_NAME = NAME_PREFIX + "." + CREATE_LOBBY_COMMAND_NAME;


    // Leave a lobby over a network configuration
    private static final String LEAVE_LOBBY_COMMAND_NAME = "leaveLobby";
    public static final String LEAVE_LOBBY_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + LEAVE_LOBBY_COMMAND_NAME;
    public static final String LEAVE_LOBBY_STATE_NAME = NAME_PREFIX + "." + LEAVE_LOBBY_COMMAND_NAME;

    // Leave a lobby over a network configuration
    private static final String START_LOBBY_COMMAND_NAME = "startLobby";
    public static final String START_LOBBY_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + START_LOBBY_COMMAND_NAME;
    public static final String START_LOBBY_STATE_NAME = NAME_PREFIX + "." + START_LOBBY_COMMAND_NAME;


    // Join a lobby over a network configuration
    private static final String JOIN_LOBBY_COMMAND_NAME = "joinLobby";
    public static final String JOIN_LOBBY_HELP_MESSAGE = HELP_MESSAGES_PREFIX + "." + JOIN_LOBBY_COMMAND_NAME;
    public static final String JOIN_LOBBY_STATE_NAME = NAME_PREFIX + "." + JOIN_LOBBY_COMMAND_NAME;
}
