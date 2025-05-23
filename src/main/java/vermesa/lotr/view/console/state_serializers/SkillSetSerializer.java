package vermesa.lotr.view.console.state_serializers;

import vermesa.lotr.model.skills.Skill;
import vermesa.lotr.model.skills.SkillSet;

public class SkillSetSerializer {

    public static String serialize(SkillSet skillSet, int leadingSpaceCount) {
        StringBuilder sb = new StringBuilder();

        var skillCounts = skillSet.getSkillCounts();
        for (var skill : Skill.values()) {
            int skillCount = skillCounts[skill.ordinal()];
            if (skillCount == 0) continue;

            sb.append(skill.toString())
                    .append(": ")
                    .append(skillCounts[skill.ordinal()])
                    .append(", ");
        }

        if (sb.isEmpty()) return "";

        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
