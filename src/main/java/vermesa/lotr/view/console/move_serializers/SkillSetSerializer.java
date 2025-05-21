package vermesa.lotr.view.console.move_serializers;

import vermesa.lotr.model.skills.Skill;
import vermesa.lotr.model.skills.SkillSet;

public class SkillSetSerializer {

    public static String serialize(SkillSet skillSet, int leadingSpaceCount) {
        StringBuilder sb = new StringBuilder();

        //var leadingSpaces = " ".repeat(leadingSpaceCount);
        // sb.append(leadingSpaces);
        var skillCounts = skillSet.getSkillCounts();
        for (var skill : Skill.values()) {
            sb.append(skill.toString())
                    .append(": ")
                    .append(skillCounts[skill.ordinal()])
                    .append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        //sb.deleteCharAt(sb.length());
        return sb.toString();
    }
}
