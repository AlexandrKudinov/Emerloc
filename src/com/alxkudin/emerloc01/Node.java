package com.alxkudin.emerloc01;

import java.util.Objects;
import java.util.Random;

import static com.alxkudin.emerloc01.NodeType.*;


public class Node {
    private Random random = new Random();
    private int X;
    private int Y;
    private Node left;
    private Node right;
    private Node up;
    private Node down;
    private House house;
    private Pipeline pipeline;
    private Pipe pipe;
    private NodeType nodeType = PIPELINE_BLOCK;

    public Node(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }

    public Pipe getPipe() {
        return pipe;
    }

    public void setType(NodeType nodeType) {
        this.nodeType = nodeType;
    }


    public void setRandomAsHouseBlock() {
        if (random.nextInt(2) == 1)
            this.nodeType = HOUSE_BLOCK;
    }

    public NodeType getType() {
        return nodeType;
    }


    public boolean isHouseBlock() {
        return nodeType == HOUSE_BLOCK ? true : false;
    }

    public boolean isHouse() {
        return nodeType == HOUSE;
    }

    public boolean isPipelineBlock() {
        return nodeType == PIPELINE_BLOCK ? true : false;
    }

    public boolean isPipeline() {
        return nodeType == PIPELINE ? true : false;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public House getHouse() {
        return house;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setDownNode(Node down) {
        this.down = down;
    }

    public void setUpNode(Node up) {
        this.up = up;
    }

    public void setLeftNode(Node left) {
        this.left = left;
    }

    public void setRightNode(Node right) {
        this.right = right;
    }

    public Node getDownNode() {
        return down;
    }

    public Node getDoubleDownNode() {
        return getDownNode().down;
    }

    public Node getLeftNode() {
        return left;
    }

    public Node getDoubleLeftNode() {
        return getLeftNode().left;
    }

    public Node getRightNode() {
        return right;
    }

    public Node getDoubleRightNode() {
        return getRightNode().right;
    }

    public Node getUpNode() {
        return up;
    }

    public Node getDoubleUpNode() {
        return getUpNode().up;
    }


    public Node getLeftUpNode() {
        return getLeftNode().up;
    }

    public Node getLeftDownNode() {
        return getLeftNode().down;
    }

    public Node getRightUpNode() {
        return getRightNode().up;
    }

    public Node getRightDownNode() {
        return getRightNode().down;
    }


    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }


    public boolean leftNodeTypeIs(NodeType fragmentType) {
        return left.getType() == fragmentType ? true : false;
    }

    public boolean doubleLeftNodeTypeIs(NodeType fragmentType) {
        return left.getLeftNode().getType() == fragmentType ? true : false;
    }

    public boolean upNodeTypeIs(NodeType fragmentType) {
        return up.getType() == fragmentType ? true : false;
    }

    public boolean doubleUpNodeTypeIs(NodeType fragmentType) {
        return up.getUpNode().getType() == fragmentType ? true : false;
    }

    public boolean rightNodeTypeIs(NodeType fragmentType) {
        return right.getType() == fragmentType ? true : false;
    }

    public boolean doubleRightNodeTypeIs(NodeType fragmentType) {
        return right.getRightNode().getType() == fragmentType;
    }

    public boolean downNodeTypeIs(NodeType fragmentType) {
        return down.getType() == fragmentType;
    }

    public boolean doubleDownNodeTypeIs(NodeType fragmentType) {
        return down.getDownNode().getType() == fragmentType;
    }

    public boolean leftUpNodeTypeIs(NodeType fragmentType) {
        return left.getUpNode().getType() == fragmentType ? true : false;
    }

    public boolean leftDownNodeTypeIs(NodeType fragmentType) {
        return left.getDownNode().getType() == fragmentType ? true : false;
    }

    public boolean rightUpNodeTypeIs(NodeType fragmentType) {
        return right.getUpNode().getType() == fragmentType ? true : false;
    }

    public boolean rightDownNodeTypeIs(NodeType fragmentType) {
        return right.getDownNode().nodeType == fragmentType ? true : false;
    }


    public boolean typeIs(NodeType fragmentType) {
        return this.nodeType == fragmentType ? true : false;
    }


    public boolean twoLeftNodesTypesAre(NodeType fragmentType) {
        return (this.getLeftNode().typeIs(fragmentType) && this.getDoubleLeftNode().typeIs(fragmentType));
    }


    public boolean twoRightNodesTypesAre(NodeType fragmentType) {
        return (this.getRightNode().typeIs(fragmentType) && this.getDoubleRightNode().typeIs(fragmentType));
    }

    public boolean twoUpNodesTypesAre(NodeType fragmentType) {
        return (this.getUpNode().typeIs(fragmentType) && this.getDoubleUpNode().typeIs(fragmentType));
    }

    public boolean twoDownNodesTypesAre(NodeType fragmentType) {
        return (this.getDownNode().typeIs(fragmentType) && this.getDoubleDownNode().typeIs(fragmentType));
    }

    public boolean twoLeftNodesTypesAreNot(NodeType fragmentType) {
        return (!this.getLeftNode().typeIs(fragmentType) && !this.getDoubleLeftNode().typeIs(fragmentType));
    }


    public boolean twoRightNodesTypesAreNot(NodeType fragmentType) {
        return (!this.getRightNode().typeIs(fragmentType) && !this.getDoubleRightNode().typeIs(fragmentType));
    }

    public boolean twoUpNodesTypesAreNot(NodeType fragmentType) {
        return (!this.getUpNode().typeIs(fragmentType) && !this.getDoubleUpNode().typeIs(fragmentType));
    }

    public boolean twoDownNodesTypesAreNot(NodeType fragmentType) {
        return (!this.getDownNode().typeIs(fragmentType) && !this.getDoubleDownNode().typeIs(fragmentType));
    }



    public boolean thisAndUpNodesTypeIs(NodeType fragmentType) {
        return (this.typeIs(fragmentType) && this.upNodeTypeIs(fragmentType));
    }

    public boolean thisAndDownNodesTypesAre(NodeType fragmentType) {
        return (this.typeIs(fragmentType) && this.downNodeTypeIs(fragmentType));
    }

    public boolean thisAndLeftNodesTypesAre(NodeType fragmentType) {
        return (this.typeIs(fragmentType) && this.leftNodeTypeIs(fragmentType));
    }

    public boolean thisAndRightNodesTypesAre(NodeType fragmentType) {
        return (this.typeIs(fragmentType) && this.rightNodeTypeIs(fragmentType));
    }


    public void setEqualType(Node fragment) {
        this.nodeType = fragment.getType();
    }


    public String toString() {
        return X + ", " + Y + ", " + nodeType;
    }

    public boolean areaIsEmpty() {
        return (!this.twoLeftNodesTypesAre(HOUSE_BLOCK)) &&
                (!this.doubleUpNodeTypeIs(HOUSE_BLOCK)) &&
                (!this.twoDownNodesTypesAre(HOUSE_BLOCK)) &&
                (!this.twoRightNodesTypesAre(HOUSE_BLOCK)) &&
                (!this.rightUpNodeTypeIs(HOUSE_BLOCK)) &&
                (!this.rightDownNodeTypeIs(HOUSE_BLOCK)) &&
                (!this.leftUpNodeTypeIs(HOUSE_BLOCK)) &&
                (!this.leftDownNodeTypeIs(HOUSE_BLOCK));
    }

    public boolean areaContainHouseBlocks() {
        return (this.twoLeftNodesTypesAre(HOUSE_BLOCK)) ||
                (this.twoUpNodesTypesAre(HOUSE_BLOCK)) ||
                (this.doubleDownNodeTypeIs(HOUSE_BLOCK)) ||
                (this.doubleRightNodeTypeIs(HOUSE_BLOCK)) ||
                (this.rightUpNodeTypeIs(HOUSE_BLOCK)) ||
                (this.rightDownNodeTypeIs(HOUSE_BLOCK)) ||
                (this.leftUpNodeTypeIs(HOUSE_BLOCK)) ||
                (this.leftDownNodeTypeIs(HOUSE_BLOCK));
    }

    public boolean partOfAreaIsEmpty() {
        return (this.downNodeTypeIs(HOUSE_BLOCK) && !this.leftUpNodeTypeIs(HOUSE_BLOCK) &&
                !this.upNodeTypeIs(HOUSE_BLOCK) && !this.rightUpNodeTypeIs(HOUSE_BLOCK)) ||
                (this.rightNodeTypeIs(HOUSE_BLOCK) && !this.leftUpNodeTypeIs(HOUSE_BLOCK) &&
                        !this.leftNodeTypeIs(HOUSE_BLOCK) && !this.leftDownNodeTypeIs(HOUSE_BLOCK)) ||
                (this.leftNodeTypeIs(HOUSE_BLOCK) && !this.rightUpNodeTypeIs(HOUSE_BLOCK) &&
                        !this.rightNodeTypeIs(HOUSE_BLOCK) && !this.rightDownNodeTypeIs(HOUSE_BLOCK)) ||
                (this.upNodeTypeIs(HOUSE_BLOCK) && !this.leftDownNodeTypeIs(HOUSE_BLOCK) &&
                        !this.downNodeTypeIs(HOUSE_BLOCK) && !this.rightDownNodeTypeIs(HOUSE_BLOCK));
    }

    public boolean houseBlockCanBeAdded() {
        return (this.getLeftNode().thisAndUpNodesTypeIs(HOUSE_BLOCK) && this.upNodeTypeIs(HOUSE_BLOCK) && !this.rightNodeTypeIs(HOUSE_BLOCK) &&
                !this.rightDownNodeTypeIs(HOUSE_BLOCK) && !this.downNodeTypeIs(HOUSE_BLOCK)) ||

                (this.getUpNode().thisAndRightNodesTypesAre(HOUSE_BLOCK) && this.rightNodeTypeIs(HOUSE_BLOCK) && !this.leftNodeTypeIs(HOUSE_BLOCK)
                        && !this.leftDownNodeTypeIs(HOUSE_BLOCK) && !this.downNodeTypeIs(HOUSE_BLOCK)) ||

                (this.downNodeTypeIs(HOUSE_BLOCK) && this.rightDownNodeTypeIs(HOUSE_BLOCK) && this.rightNodeTypeIs(HOUSE_BLOCK) &&
                        this.leftNodeTypeIs(PIPELINE_BLOCK) && this.leftUpNodeTypeIs(PIPELINE_BLOCK) && this.upNodeTypeIs(PIPELINE_BLOCK)) ||

                (this.leftNodeTypeIs(HOUSE_BLOCK) && this.leftDownNodeTypeIs(HOUSE_BLOCK) && this.downNodeTypeIs(HOUSE_BLOCK) &&
                        this.rightNodeTypeIs(PIPELINE_BLOCK) && this.rightUpNodeTypeIs(PIPELINE_BLOCK) && this.upNodeTypeIs(PIPELINE_BLOCK)) ||

                (this.upNodeTypeIs(HOUSE_BLOCK) && this.leftNodeTypeIs(HOUSE_BLOCK) && this.leftUpNodeTypeIs(HOUSE_BLOCK) &&
                        this.rightNodeTypeIs(HOUSE_BLOCK) && this.rightUpNodeTypeIs(HOUSE_BLOCK)) ||

                (this.downNodeTypeIs(HOUSE_BLOCK) && this.leftDownNodeTypeIs(HOUSE_BLOCK) && this.leftNodeTypeIs(HOUSE_BLOCK) &&
                        this.leftUpNodeTypeIs(HOUSE_BLOCK) && this.upNodeTypeIs(HOUSE_BLOCK));
    }

    public boolean columnNodeAreaContainHouseBlocks() {
        return (this.getLeftNode().twoUpNodesTypesAre(HOUSE_BLOCK)) ||
                (this.getRightNode().twoUpNodesTypesAre(HOUSE_BLOCK)) ||
                (this.getLeftNode().twoDownNodesTypesAre(HOUSE_BLOCK)) ||
                (this.getRightNode().twoDownNodesTypesAre(HOUSE_BLOCK)) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node fragment = (Node) o;
        return X == fragment.X &&
                Y == fragment.Y &&
                nodeType == fragment.nodeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y, nodeType);
    }
}

