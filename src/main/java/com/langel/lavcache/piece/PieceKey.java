package com.langel.lavcache.piece;

/**
 * @author rick(lonelyangel.jcw @ gamil.com)
 * @date 2018/9/26 下午4:20
 **/
public class PieceKey {


    private String field;

    private String val;

    public PieceKey(String field,
                    String val) {
        this.field = field;
        this.val = val;
    }

    public String field() {
        return field;
    }

    public String val() {
        return this.val;
    }

}
