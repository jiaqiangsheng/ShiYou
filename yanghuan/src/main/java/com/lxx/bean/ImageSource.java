package com.lxx.bean;

import java.io.Serializable;

/**
 * Created by 潇 on 2016/5/13.
 */
public class ImageSource implements Serializable{

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getImageweidth() {
            return imageweidth;
        }

        public void setImageweidth(int imageweidth) {
            this.imageweidth = imageweidth;
        }

        public int getImageheight() {
            return imageheight;
        }

        public void setImageheight(int imageheight) {
            this.imageheight = imageheight;
        }

        public ImageSource(String imageUrl, int imageweidth) {
            this.imageUrl = imageUrl;
            this.imageweidth = imageweidth;
        }

        public ImageSource(String imageUrl, int imageheight, int imageweidth) {
            this.imageUrl = imageUrl;
            this.imageheight = imageheight;
            this.imageweidth = imageweidth;
        }


        String imageUrl;
        int imageheight=0;
        int imageweidth;


}
