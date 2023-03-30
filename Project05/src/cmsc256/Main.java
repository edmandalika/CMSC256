package cmsc256;

import bridges.base.BinTreeElement;

public class Main {
    public static void main(String[] args) {

        //Project5.buildParseTree("( 3 + ( 4 * 5 ) )");

        BinTreeElement<String> tree = Project5.buildParseTree("( ( 7 + 3 ) * ( 5 - 2 ) )");
        System.out.println(Project5.getEquation(tree));


    }
}