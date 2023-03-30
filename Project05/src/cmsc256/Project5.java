package cmsc256;

import bridges.base.BinTreeElement;

import java.util.ArrayList;
import java.util.Stack;

public class Project5<E extends Comparable<E>> {

    private BinTreeElement<E> root;
    private int size;

    private String rebuiltExp = "";

    public Project5() {
        clear();
    }

    public Project5(BinTreeElement<E> root, int size, String rebuiltExp) {
        clear();
    }

    public void clear(){
        this.root = null;
        this.size = 0;
        this.rebuiltExp ="";
    }

    public static bridges.base.BinTreeElement<String> buildParseTree(String expression){
        Stack<BinTreeElement<String>> myStack = new Stack<>();
        Project5<String> currentTree = new Project5<>();
        BinTreeElement<String> currNode = new BinTreeElement<>("");
        currentTree.root = currNode;
        myStack.push(currNode);


        String[] splitExp = expression.split(" ");
        for(String token : splitExp){
            if (token.equalsIgnoreCase("(")){
                currNode.setLeft(new BinTreeElement<>(" "));
                myStack.push(currNode);
                currNode = currNode.getLeft();
            }
            else if(token.equalsIgnoreCase(")")){
                currNode = myStack.pop();
            }
            else if(isOperator(token)) {
                currNode.setValue(token);
                currNode.setRight(new BinTreeElement<>(" "));
                myStack.push(currNode);
                currNode = currNode.getRight();
            }
            else {
                currNode.setValue(token);
                BinTreeElement<String> parentNode = myStack.pop();
                currNode = parentNode;
            }
        }

        return currNode;
    }

    public static boolean isOperator(String token){
        boolean result = false;
        String[] operators = {"+","-","*","/"};
        for (String operator : operators) {
            if(operator.equalsIgnoreCase(token)){
                result = true;
            }
        }
        return result;
    }

    public static double evaluate(bridges.base.BinTreeElement<String> tree){return 0.0;}

    public static String getEquation(bridges.base.BinTreeElement<String> tree){
        if (tree == null){
            return "";
        }
        else {
            BinTreeElement<String> goingLeft = (BinTreeElement<String>) tree.getChild(0);
            String left = getEquation(goingLeft);

            String out = tree.getValue();

            BinTreeElement<String> goingRight = (BinTreeElement<String>) tree.getChild(1);
            String right = getEquation(goingRight);

            if (left.length() == 0 && right.length() == 0){
                return left + out + right ;
            }
            else{
                return "(" + left + out + right + ")";
            }

        }
    }

}
