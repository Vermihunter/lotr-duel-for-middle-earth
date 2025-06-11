package vermesa.lotr;


import java.util.Collection;

interface Consumer<T> {
    void accept(T t);
}

class First {
}

class Second extends First {
}

class A {
    First aMethod(First arg) {
        return null;
    }
}

class B extends A {
    static <E> void addDefaults(Collection<? super E> target, E defaultValue) {
        // We know `target` can accept E (or some supertype of E).
        // Even if `target` is List<Object>, we can still add an E.
        target.add(defaultValue);
        Consumer<First> animalConsumer = a -> System.out.println(a.getClass().getSimpleName());
        Consumer<? super Second> dogConsumer = animalConsumer;

    }

    @Override
    Second aMethod(First arg) {

        return null;
    }

    /*
    <T> T[] makeArray(T t) {
        return new T[100]; // ← impossible
    }
    */
}

/*
class First { }
class Second extends First { }

class A {
    First aMethod(First arg) {
        System.out.println("A: got a First; returning a First");
        return new First();
    }
}

class B extends A {
    @Override
    Second aMethod(First arg) {
        System.out.println("B: got a First; returning a Second");
        return new Second();
    }
}
*/

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        /*
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON from file
        JsonConfig config = objectMapper.readValue(new File("DefaultConfig.json"), JsonConfig.class);
        //System.out.println(config);
        Random rand = new Random(3);
        var game = config.createGame(rand);

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        //Scanner sc = new Scanner(System.in);
        //  int moveInd = sc.nextInt();

        //while(game.getState().getCurrentRoundNumber() == 1) {
        while (game.getState().getCurrentGameState() == CurrentGameState.HAS_NOT_ENDED) {
            var moveOptions = game.getPossibleMoves();
            ArrayList<IAction> actions = new ArrayList<>();
            for (var moveOptionGroup : moveOptions) {


                int moveInd = rand.nextInt(moveOptionGroup.size());
                var moveOption = moveOptionGroup.get(moveInd);

                actions.add(moveOption);
            }


            // System.out.println(move);
            System.out.println(objectMapper.writeValueAsString(actions) + ",");
            game.makeMove(actions);
        }


        System.out.println(game.getState().getCurrentRoundNumber());

        */
    }
}