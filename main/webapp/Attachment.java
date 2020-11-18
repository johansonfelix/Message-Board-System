package com.company;

import java.io.Serializable;

public class Attachment implements Serializable {
    private int postID;
    private String original_file_name;
    private long file_size;
    private String mediaType;
    private byte[] attachment;

    public Attachment(int postID, String original_file_name, long file_size, String mediaType, byte[] attachment) {
        this.postID = postID;
        this.original_file_name = original_file_name;
        this.file_size = file_size;
        this.mediaType = mediaType;
        this.attachment = attachment;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getOriginal_file_name() {
        return original_file_name;
    }

    public void setOriginal_file_name(String original_file_name) {
        this.original_file_name = original_file_name;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
