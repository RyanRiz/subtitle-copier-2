/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subcp2.service;

import java.util.Stack;
/**
 *
 * @author Ryan Rizky
 */
public class UndoManager<T> {
    private Stack<T> undoStack = new Stack<>();
    private Stack<T> redoStack = new Stack<>();

    public void addToUndoStack(T item) {
        undoStack.push(item);
        redoStack.clear(); // Clear redo stack when a new action is added to undo stack
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public T undo() {
        if (canUndo()) {
            T item = undoStack.pop();
            redoStack.push(item);
            return item;
        }
        return null;
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    public T redo() {
        if (canRedo()) {
            T item = redoStack.pop();
            undoStack.push(item);
            return item;
        }
        return null;
    }

    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }
}

