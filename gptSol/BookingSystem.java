package gptSol;


class BookingSystem {
    private TreeNode root;

    public BookingSystem() {
        root = null;
    }
    // Method to insert a new booking into the BST
    public void insert(int seatNumber, String ownerName) {
        root = insertRec(root, seatNumber, ownerName);
    }
    private TreeNode insertRec(TreeNode root, int seatNumber, String ownerName) {
        if (root == null) {
            root = new TreeNode(seatNumber, ownerName);
            return root;
        }

        if (seatNumber < root.seatNumber)
            root.left = insertRec(root.left, seatNumber, ownerName);
        else if (seatNumber > root.seatNumber)
            root.right = insertRec(root.right, seatNumber, ownerName);

        return root;
    }

    // Method to delete a booked seat by seat number
    public void delete(int seatNumber) {
        root = deleteRec(root, seatNumber);
    }

    private TreeNode deleteRec(TreeNode root, int seatNumber) {
        if (root == null)
            return root;

        if (seatNumber < root.seatNumber)
            root.left = deleteRec(root.left, seatNumber);
        else if (seatNumber > root.seatNumber)
            root.right = deleteRec(root.right, seatNumber);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.seatNumber = minValue(root.right);

            root.right = deleteRec(root.right, root.seatNumber);
        }

        return root;
    }

    private int minValue(TreeNode root) {
        int minv = root.seatNumber;
        while (root.left != null) {
            minv = root.left.seatNumber;
            root = root.left;
        }
        return minv;
    }

    // Method to count the number of available seats
    public int countAvailableSeats() {
        return 155 - countBookedSeats(root);
    }

    private int countBookedSeats(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countBookedSeats(root.left) + countBookedSeats(root.right);
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
