package vermesa.lotr.model.chapter_cards;

import vermesa.lotr.model.actions.IAction;

import java.util.ArrayList;
import java.util.List;

public class ChapterCardContext {
    private final List<List<IAction>> cardContexts = new ArrayList<>(ChapterCardColors.values().length);

    public ChapterCardContext() {
        for (int i = 0; i < ChapterCardColors.values().length; i++) {
            cardContexts.add(new ArrayList<>());
        }
    }

    public List<IAction> getCardContext(ChapterCardColors color) {
        return cardContexts.get(color.ordinal());
    }
}
