package tools.mscript.syntax;

public enum Comment {
    SINGLE(Character.toString(TextSpecifications.COMMENT.getSymbol())),
    BULK_OPEN(Character.toString(TextSpecifications.COMMENT.getSymbol()) +
            TextSpecifications.COMMENT.getSymbol() + '!'),
    BULK_CLOSE('!' + Character.toString(TextSpecifications.COMMENT.getSymbol()) +
            TextSpecifications.COMMENT.getSymbol());

    private final String signature;
    Comment(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return this.signature;
    }
}
