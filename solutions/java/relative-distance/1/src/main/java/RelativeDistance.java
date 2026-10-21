import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class RelativeDistance {

    class Node {

        private String nodePerson;
        private List<String> nodeParents;
        private List<String> nodeChildren;

        public Node() {
            nodePerson = "";
            nodeParents = new ArrayList<>();
            nodeChildren = new ArrayList<>();
        }

        public String getNodePerson() {
            return nodePerson;
        }
        public void setNodePerson(String nodePerson) {
            this.nodePerson = nodePerson;
        }
        public List<String> getNodeParents() {
            return nodeParents;
        }
        public void setNodeParents(List<String> nodeParents) {
            this.nodeParents = nodeParents;
        }
        public List<String> getNodeChildren() {
            return nodeChildren;
        }
        public void setNodeChildren(List<String> nodeChildren) {
            this.nodeChildren = nodeChildren;
        }
        public boolean hasParentNodes() {
            return nodeParents != null && !nodeParents.isEmpty();
        }
    }

    class DegreesOfSeparationState {

        private int dos;
        private int ancestorsDoS;
        private int descendantsDoS;
        private boolean ancestorsDoSDetermined;
        private boolean descendantsDoSDetermined;
        private String originatorDescendant;

        public DegreesOfSeparationState () {
            this.dos = 0;
            this.ancestorsDoS = 0;
            this.descendantsDoS = 0;
            this.ancestorsDoSDetermined = false;
            this.descendantsDoSDetermined = false;
            this.originatorDescendant = "";
        }

        public int getDos() {
            return dos;
        }
        public void setDos(int dos) {
            this.dos = dos;
        }
        public int getAncestorsDoS() {
            return ancestorsDoS;
        }
        public void setAncestorsDoS(int ancestorsDoS) {
            this.ancestorsDoS = ancestorsDoS;
        }
        public int getDescendantsDoS() {
            return descendantsDoS;
        }
        public void setDescendantsDoS(int descendantsDoS) {
            this.descendantsDoS = descendantsDoS;
        }
        public boolean isAncestorsDoSDetermined() {
            return ancestorsDoSDetermined;
        }
        public void setAncestorsDoSDetermined(boolean ancestorsDoSDetermined) {
            this.ancestorsDoSDetermined = ancestorsDoSDetermined;
        }
        public boolean isDescendantsDoSDetermined() {
            return descendantsDoSDetermined;
        }
        public void setDescendantsDoSDetermined(boolean descendantsDoSDetermined) {
            this.descendantsDoSDetermined = descendantsDoSDetermined;
        }
        public String getOriginatorDescendant() {
            return originatorDescendant;
        }
        public void setOriginatorDescendant(String originatorDescendant) {
            this.originatorDescendant = originatorDescendant;
        }
    }

    private final Map<String, List<String>> familyTree;
        private DegreesOfSeparationState dosState;

    RelativeDistance(Map<String, List<String>> familyTree) {
        this.familyTree = familyTree;
        this.dosState = new DegreesOfSeparationState();
    }

    /*
     * This method determines the degreeOfSeparation between person A and person B.
     *
     * It assumes person A and person B has no ordering - either could be the ancestor or the descendant.
     *
     * Processing Logic:
     * 1) Check if siblings
     * 2) Check if direct descendant
     *     a) Process down the tree (Children constraint: 0, 1 or 2 only)
     * 3) Move up an ancestor level, and then check ancestor's descendants
     *     a) Process up the tree (Parents constraint: 0, 1 or 2 only)
     *     b) If match is found via ancestors then descendants, then inversion point degree of separation is:
     *         sibling-sibling, not
     *         sibling-parent-sibling
     *    c) Not necessary to process across the tree (siblings).
     *         Processing simpler to follow inversion point sibling-parent-sibling.
     *         Hence, decrement degree of separation count as per b).
while     */
    public int degreeOfSeparation(String personA, String personB) {

        if (checkIfSiblings(personA, personB)) {
            return 1;
        }

        Node startingNode = ascertainNodeDetails(personA);

        // Process descendants of starting node
        getDescendantsDegreeOfSeparation(startingNode, personB, 0);
        if (dosState.getDescendantsDoS() > 0) {
            dosState.setDos(dosState.getDescendantsDoS());
        }

        // Process ancestors, if necessary:
        // - When degree of separation not yet determined (i.e. while descendantsDoS keeps returning 0)
        // - Move up 1 ancestor at a time and process all descendants from there.
        getAncestorsDegreeOfSeparation(startingNode, personB, 0);

        return dosState.getDos() == 0 ? -1 : dosState.getDos();
    }

    boolean checkIfSiblings(String personA, String personB) {
        return familyTree.values().stream()
                .anyMatch(list -> list.contains(personA) && list.contains(personB));
    }

    private void getDescendantsDegreeOfSeparation(Node node, String person, int descendantCount) {
        if (node.getNodePerson() != null) {
            if (node.getNodeChildren() != null) {
                // Node has child/children, so process children
                descendantCount++;
                if (node.getNodeChildren().stream().anyMatch(person::equals)) {
                    dosState.setDescendantsDoSDetermined(true);
                    // Tracking descendantDoS for later ancestor process path determination,
                    // and for breakdown information between ancestors and descendants.
                    dosState.setDescendantsDoS(descendantCount);
                }
                boolean endOfBranchReached = false;
                while (!dosState.isDescendantsDoSDetermined() && !endOfBranchReached) {
                    // Process each child node recursively!
                    for (String child : node.getNodeChildren()) {
                        if (!dosState.isDescendantsDoSDetermined() && !child.equals(dosState.getOriginatorDescendant())) {
                            getDescendantsDegreeOfSeparation(ascertainNodeDetails(child), person, descendantCount);
                        }
                    }
                    endOfBranchReached = true;
                }
            }
        }
    }

    public void getAncestorsDegreeOfSeparation(Node currentNode, String personB, int ancestorCount) {
        if (dosState.getDescendantsDoS() == 0) {
            if (currentNode.hasParentNodes()) {
                ancestorCount++;
                ListIterator<String> parents = currentNode.getNodeParents().listIterator();
                while (parents.hasNext() && !dosState.ancestorsDoSDetermined) {
                    dosState.setOriginatorDescendant(currentNode.getNodePerson());
                    String parent = parents.next();
                    currentNode = ascertainNodeDetails(parent);
                    getDescendantsDegreeOfSeparation(currentNode, personB, 0);
                    if (dosState.getDescendantsDoS() == 0) {
                        getAncestorsDegreeOfSeparation(currentNode, personB, ancestorCount);
                    } else {
                        dosState.setAncestorsDoSDetermined(true);
                        // Allow for sibling-sibling rather than child-parent-child degreeOfSeparation.
                        dosState.setAncestorsDoS(ancestorCount - 1);
                        dosState.setDos(dosState.getAncestorsDoS() + dosState.getDescendantsDoS());
                    }
                }
            }
        }
    }

    private Node ascertainNodeDetails(String person) {
        Node node = new Node();
        node.setNodePerson(person);
        // Find Children
        if (familyTree.containsKey(person)) {
            node.setNodeChildren(familyTree.get(person));
        }
        // Find Parents
        Optional<List<String>> parentNodes =
                Optional.of(familyTree.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().contains(person))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList()));
        parentNodes.ifPresent(node::setNodeParents);
        return node;
    }
}
