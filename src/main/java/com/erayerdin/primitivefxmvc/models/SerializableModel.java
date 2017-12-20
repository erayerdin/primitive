package com.erayerdin.primitivefxmvc.models;

import java.io.*;

/**
 * SerializableModel is a model based on File. It already extends Serializable and Externalizable.
 *
 * @see java.io.Serializable
 * @see java.io.Externalizable
 * @author erayerdin
 * @see java.io.File
 */
public interface SerializableModel extends Serializable, Externalizable {
    void writeObject(ObjectOutputStream out) throws IOException;
    void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException;

    /**
     * A save method which saves the object that implements SerializableModel.
     *
     * @param obj object to serialize
     * @param path path to save
     * @throws IOException
     */
    static void save(SerializableModel obj, File path) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(obj);
        objectOut.close();
        fileOut.close();
    }

    /**
     * A load method which loads the object from a path.
     *
     * @param path path to load from
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static SerializableModel load(File path) throws IOException, ClassNotFoundException {
        SerializableModel obj = null;

        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        obj = (SerializableModel) objectIn.readObject();
        objectIn.close();
        fileIn.close();

        return obj;
    }
}
