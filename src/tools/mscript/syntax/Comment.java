package tools.mscript.syntax;

/**
 * <b>Defines how to open a comment</b><br/>
 * See the {@link tools.mscript.Script documentation} for more information on comments.
 * @implNote Do not change! There is no need to add another type of comment to this language.
 */
public enum Comment {
    /** Single line comment */
    SINGLE(Character.toString(TextSpecifications.COMMENT.getSymbol())),

    /** Opening a multi-line (grouped) comment */
    BULK_OPEN(Character.toString(TextSpecifications.COMMENT.getSymbol()) +
            TextSpecifications.COMMENT.getSymbol() + '!'),

    /** Closing a multi-line (grouped) comment */
    BULK_CLOSE('!' + Character.toString(TextSpecifications.COMMENT.getSymbol()) +
            TextSpecifications.COMMENT.getSymbol());

    /** The signature used to call each specific {@link Comment} */
    private final String signature;

    Comment(String signature) {
        this.signature = signature;
    }

    /**
     * <b>Gets the signature to the specified {@link Comment} type</b>
     * @return either "#", "##!", or "!##" depending on the class
     */
    public String getSignature() {
        return this.signature;
    }
}
