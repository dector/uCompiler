package ua.org.dector.ucompiler.queries;

//todo: I'm looking like some shirt. REFACTOR ME!!! O_o

/**
 * @author dector
 */
public class QueryFilter {
    /*
     * Value -> | Long | Double | String |
     *  type    |      |       |         |
     *          |      |       |         |
     * Field |  |      |       |         |
     *       V  |      |       |         |
     *          |      |       |         |
     *       ID |  (+) |       |         |
     *     NAME |   +  |   +   |   (+)   |
     *    VALUE |   +  |  (+)  |         |
     */
    public static boolean[][] FIELD_AND_VALUE_TYPE_COMPATIBILITY = {{true, false, false},
                                                                    {true, true, true},
                                                                    {true, true, false}};

    private QueryFilterField field;
    private QueryFilterCondition condition;
    private QueryFilterValue value;

    public QueryFilter(QueryFilterField field, QueryFilterCondition condition, QueryFilterValue value) {
        if (isFieldValueCompatibility(field, value)) {
            this.field = field;
            this.condition = condition;
            this.value = value;
        } else {
            //todo: I'm feeling so empty. Implement me!
        }
    }

    private boolean isFieldValueCompatibility(QueryFilterField field, QueryFilterValue value) {
        byte fieldIndex;
        byte typeIndex;

        switch (field) {
            case ID: fieldIndex = 0; break;
            case NAME: fieldIndex = 1; break;
            case VALUE: fieldIndex = 2; break;
            default: return false; // /!\ DANGER!!! RETURN used!
        }

        Class valueClass = value.getClass();
        if (valueClass.equals(Long.class)) {
            typeIndex = 0;
        } else if (valueClass.equals(Double.class)) {
            typeIndex = 1;
        } else if (valueClass.equals(String.class)) {
            typeIndex = 2;
        } else {
            return false; // /!\ DANGER!!! RETURN used!
        }

        return FIELD_AND_VALUE_TYPE_COMPATIBILITY[fieldIndex][typeIndex];
    }
}
