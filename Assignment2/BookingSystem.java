package Assignment2;


class BookingSystem {
    private TreeNode root;

    public BookingSystem() {
        root = null; // root initially null
    }
    // Method to insert booking
    public void insert(int seatNumber, String ownerName) {
        root = insertRec(root, seatNumber, ownerName); // assigns node to insert to the returned root node
        System.out.println("Booked seat number: " + seatNumber + "\nFor: " + ownerName + ".\n");
    }

    // Recursive insert method
    private TreeNode insertRec(TreeNode root, int seatNumber, String ownerName) { // takes current root node of the sub tree, the seat number, and the owners name 
        if (root == null) { // If leaf node or empty sub tree is reached
            root = new TreeNode(seatNumber, ownerName); // Create new node, this new node becomes the root node of the new sub tree
            return root; // return new root node
        }

        if (seatNumber < root.seatNumber){// if true then recursively call insertRec on the left sub tree of the current node 'root.left'
            root.left = insertRec(root.left, seatNumber, ownerName); 
        }     
        else if (seatNumber > root.seatNumber){// if true then recursively call insertRec on the right sub tree of the current node 'root.right'
            root.right = insertRec(root.right, seatNumber, ownerName);
            }
        return root; // Appropriate position is found. (either left or right child of parent node)
    }

    // Method to delete a booked seat by seat number
    public void delete(int seatNumber) {
        root = deleteRec(root, seatNumber); // assigns node to be deleted to the returned root node
        System.out.println("Unbooked seat number: " + seatNumber + ".\n");
    }

    private TreeNode deleteRec(TreeNode root, int seatNumber) { // takes current root node of the sub tree, and the seat number
        if (root == null){ // If sub tree is empty || seat number doesn't exist
            System.out.println("Seat number doesn't exist");
            return root;
        }
        if (seatNumber < root.seatNumber)
            root.left= deleteRec(root.left, seatNumber); // if true then recursively call deleteRec on the left sub tree of the current node 'root.left'
        else if (seatNumber > root.seatNumber)
            root.right=deleteRec(root.right, seatNumber); // if true then recursively call deleteRec on the right sub tree of the current node 'root.right'
        else {
            if (root.left == null) // if current root node has no left child return right child.
                return root.right;
            else if (root.right == null) // if current root node has no right child return left child.
                return root.left;
            
                // if current root node has both left and right child
            root.seatNumber = minValue(root.right); // find minimum value of its right sub tree, replace seat number of current node with seat number of minimum value node

            root.right = deleteRec(root.right, root.seatNumber); // recursively delete minimum value node from the right sub tree
        }

        return root; 
    }

    private int minValue(TreeNode root) {
        int minVal = root.seatNumber; // initialize minimum value with root node's seat number
        while (root.left != null) { // loop as long as there is a left child until left most child node is reached
            minVal = root.left.seatNumber;
            root = root.left;
        }
        return minVal; // return left most child || Minimum value seat
    }

    // Method to count the number of available seats
    public int countAvailableSeats() {
        return 155 - countBookedSeats(root);
    }

    private int countBookedSeats(TreeNode root) {
        if (root == null){ // if root is null then return 0 booked seats
            return 0;
        }
        return 1 + countBookedSeats(root.left) + countBookedSeats(root.right); // recursively call countBookSeats on left and right sub trees. If empty return 0, if node available add 1.
        
    }

    // Method to display booked seats
    public void displayBookedSeats() {
        inorderTraversal(root);
    }

    private void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println("Seat " + root.seatNumber + ": " + root.ownerName);
            inorderTraversal(root.right);
        }
    }

    // Method to display booked seats after a specific booked seat
    public void displayBookedSeatsAfter(int seatNumber) {
        displayBookedSeatsAfterRec(root, seatNumber);
    }

    private void displayBookedSeatsAfterRec(TreeNode root, int seatNumber) {
        if (root == null)
            return;

        displayBookedSeatsAfterRec(root.left, seatNumber);

        if (root.seatNumber > seatNumber)
            System.out.println("Seat " + root.seatNumber + ": " + root.ownerName);

        displayBookedSeatsAfterRec(root.right, seatNumber);
    }
}
