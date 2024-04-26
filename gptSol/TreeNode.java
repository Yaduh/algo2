package gptSol;

class TreeNode {
    int seatNumber;
    String ownerName;
    TreeNode left, right;

    public TreeNode(int seatNumber, String ownerName) {
        this.seatNumber = seatNumber;
        this.ownerName = ownerName;
        left = right = null;
    }
    
}


