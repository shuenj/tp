package seedu.address.commons.util;

/**
 * Builds a string representation of an object that is suitable as the return value of {@link Object#toString()}.
 */
public class ToStringBuilder {
    private static final String OBJECT_PREFIX = "{";
    private static final String OBJECT_SUFFIX = "}";
    private static final String FIELD_SEPARATOR = ", ";
    private static final String FIELD_NAME_VALUE_SEPARATOR = "=";

    private final StringBuilder stringBuilder = new StringBuilder();
    private boolean hasField;

    /**
     * Constructs a {@code ToStringBuilder} whose formatted output will be prefixed with {@code objectName}.
     */
    public ToStringBuilder(String objectName) {
        stringBuilder.append(objectName).append(OBJECT_PREFIX);
    }

    /**
     * Constructs a {@code ToStringBuilder} whose formatted output will be prefixed with the
     * canonical class name of {@code object}.
     */
    public ToStringBuilder(Object object) {
        this(object.getClass().getCanonicalName());
    }

    /**
     * Adds a field name/value pair to the output string.
     *
     * @param fieldName The name of the field.
     * @param fieldValue The value of the field.
     * @return A reference to this {@code ToStringBuilder} object, allowing method calls to be chained.
     */
    public ToStringBuilder add(String fieldName, Object fieldValue) {
        if (hasField) {
            stringBuilder.append(FIELD_SEPARATOR);
        }
        stringBuilder.append(fieldName).append(FIELD_NAME_VALUE_SEPARATOR).append(fieldValue);
        hasField = true;
        return this;
    }

    /**
     * Retrieves the fields of the superclass and adds to the output string of a subclass.
     *
     * @param superToString The output string of super class.
     * @return A reference to this {@code ToStringBuilder} object that has been added
     * the attributes of its superclass.
     */
    public ToStringBuilder appendSuper(String superToString) {
        if (superToString != null) {
            int startPos = superToString.indexOf(OBJECT_PREFIX) + OBJECT_PREFIX.length();
            int endPos = superToString.lastIndexOf(OBJECT_SUFFIX);
            String combinedAttributes = superToString.substring(startPos, endPos);

            if (startPos < endPos) {
                String[] attributes = combinedAttributes.split(", ");
                for (String attribute : attributes) {
                    String[] nameValuePair = attribute.split("=");
                    this.add(nameValuePair[0], nameValuePair[1]);
                }
            }
        }
        return this;
    }

    /**
     * Returns the built formatted string representation.
     */
    @Override
    public String toString() {
        return stringBuilder.toString() + OBJECT_SUFFIX;
    }
}
