package com.fun_play.app.datamanager.bean.watch;

import android.text.TextUtils;

import java.util.List;

public class FilmDetailBean {


    private String code;
    private FilmDetailDataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FilmDetailDataBean getData() {
        return data;
    }

    public void setData(FilmDetailDataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class FilmDetailDataBean {

        private BasicBean basic;
        private BoxOfficeBean boxOffice;
        private String playState;
        private RelatedBean related;


        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public BoxOfficeBean getBoxOffice() {
            return boxOffice;
        }

        public void setBoxOffice(BoxOfficeBean boxOffice) {
            this.boxOffice = boxOffice;
        }

        public String getPlayState() {
            return playState;
        }

        public void setPlayState(String playState) {
            this.playState = playState;
        }

        public RelatedBean getRelated() {
            return related;
        }

        public void setRelated(RelatedBean related) {
            this.related = related;
        }


        public static class BasicBean {

            private String bigImage;
            private String commentSpecial;
            private ActorsBean director;
            private String img;
            private boolean is3D;
            private boolean isDMAX;
            private boolean isEggHunt;
            private boolean isFilter;
            private boolean isIMAX;
            private boolean isIMAX3D;
            private boolean isTicket;
            private String message;
            private String mins;
            private int movieId;
            private String name;
            private String nameEn;
            private double overallRating;
            private int personCount;
            private String releaseArea;
            private String releaseDate;
            private boolean sensitiveStatus;
            private int showCinemaCount;
            private int showtimeCount;
            private StageImgBean stageImg;
            private String story;
            private StyleBean style;
            private int totalNominateAward;
            private int totalWinAward;
            private String url;
            private VideoBean video;
            private List<ActorsBean> actors;
            private List<String> type;

            public String getBigImage() {
                return bigImage;
            }

            public void setBigImage(String bigImage) {
                this.bigImage = bigImage;
            }

            public String getCommentSpecial() {
                if (!TextUtils.isEmpty(commentSpecial)) {
                    return "“ " + commentSpecial + " ”";
                } else {
                    return "";
                }
            }

            public void setCommentSpecial(String commentSpecial) {
                this.commentSpecial = commentSpecial;
            }

            public ActorsBean getDirector() {
                return director;
            }

            public void setDirector(ActorsBean director) {
                this.director = director;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public boolean isIs3D() {
                return is3D;
            }

            public void setIs3D(boolean is3D) {
                this.is3D = is3D;
            }

            public boolean isIsDMAX() {
                return isDMAX;
            }

            public void setIsDMAX(boolean isDMAX) {
                this.isDMAX = isDMAX;
            }

            public boolean isIsEggHunt() {
                return isEggHunt;
            }

            public void setIsEggHunt(boolean isEggHunt) {
                this.isEggHunt = isEggHunt;
            }

            public boolean isIsFilter() {
                return isFilter;
            }

            public void setIsFilter(boolean isFilter) {
                this.isFilter = isFilter;
            }

            public boolean isIsIMAX() {
                return isIMAX;
            }

            public void setIsIMAX(boolean isIMAX) {
                this.isIMAX = isIMAX;
            }

            public boolean isIsIMAX3D() {
                return isIMAX3D;
            }

            public void setIsIMAX3D(boolean isIMAX3D) {
                this.isIMAX3D = isIMAX3D;
            }

            public boolean isIsTicket() {
                return isTicket;
            }

            public void setIsTicket(boolean isTicket) {
                this.isTicket = isTicket;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getMins() {
                if (TextUtils.isEmpty(mins)) {
                    return "未知";
                }
                return mins;
            }

            public void setMins(String mins) {
                this.mins = mins;
            }

            public int getMovieId() {
                return movieId;
            }

            public void setMovieId(int movieId) {
                this.movieId = movieId;
            }


            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNameEn() {
                return nameEn;
            }

            public void setNameEn(String nameEn) {
                this.nameEn = nameEn;
            }

            public double getOverallRating() {
                return overallRating;
            }

            public void setOverallRating(double overallRating) {
                this.overallRating = overallRating;
            }

            public int getPersonCount() {
                return personCount;
            }

            public void setPersonCount(int personCount) {
                this.personCount = personCount;
            }

            public String getReleaseArea() {
                return releaseArea;
            }

            public void setReleaseArea(String releaseArea) {
                this.releaseArea = releaseArea;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }

            public boolean isSensitiveStatus() {
                return sensitiveStatus;
            }

            public void setSensitiveStatus(boolean sensitiveStatus) {
                this.sensitiveStatus = sensitiveStatus;
            }

            public int getShowCinemaCount() {
                return showCinemaCount;
            }

            public void setShowCinemaCount(int showCinemaCount) {
                this.showCinemaCount = showCinemaCount;
            }

            public int getShowtimeCount() {
                return showtimeCount;
            }

            public void setShowtimeCount(int showtimeCount) {
                this.showtimeCount = showtimeCount;
            }

            public StageImgBean getStageImg() {
                return stageImg;
            }

            public void setStageImg(StageImgBean stageImg) {
                this.stageImg = stageImg;
            }

            public String getStory() {
                return story;
            }

            public void setStory(String story) {
                this.story = story;
            }

            public StyleBean getStyle() {
                return style;
            }

            public void setStyle(StyleBean style) {
                this.style = style;
            }

            public int getTotalNominateAward() {
                return totalNominateAward;
            }

            public void setTotalNominateAward(int totalNominateAward) {
                this.totalNominateAward = totalNominateAward;
            }

            public int getTotalWinAward() {
                return totalWinAward;
            }

            public void setTotalWinAward(int totalWinAward) {
                this.totalWinAward = totalWinAward;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public VideoBean getVideo() {
                return video;
            }

            public void setVideo(VideoBean video) {
                this.video = video;
            }

            public List<ActorsBean> getActors() {
                return actors;
            }

            public void setActors(List<ActorsBean> actors) {
                this.actors = actors;
            }

            public List<String> getType() {
                return type;
            }

            public void setType(List<String> type) {
                this.type = type;
            }

            public static class StageImgBean {
                /**
                 * count : 492
                 * list : [{"imgId":7530837,"imgUrl":"http://img5.mtime.cn/pi/2019/03/29/095718.20503556_1280X720X2.jpg"},{"imgId":7530838,"imgUrl":"http://img5.mtime.cn/pi/2019/03/29/095719.81293973_1280X720X2.jpg"},{"imgId":7530839,"imgUrl":"http://img5.mtime.cn/pi/2019/03/29/095720.56498166_1280X720X2.jpg"},{"imgId":7530840,"imgUrl":"http://img5.mtime.cn/pi/2019/03/29/095722.99339034_1280X720X2.jpg"}]
                 */

                private int count;
                private List<ImageListBean> list;

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public List<ImageListBean> getList() {
                    return list;
                }

                public void setList(List<ImageListBean> list) {
                    this.list = list;
                }


            }

            public static class StyleBean {
                /**
                 * isLeadPage : 0
                 * leadImg : https://img2.mtime.cn/mg/.jpg
                 * leadUrl :
                 */

                private int isLeadPage;
                private String leadImg;
                private String leadUrl;

                public int getIsLeadPage() {
                    return isLeadPage;
                }

                public void setIsLeadPage(int isLeadPage) {
                    this.isLeadPage = isLeadPage;
                }

                public String getLeadImg() {
                    return leadImg;
                }

                public void setLeadImg(String leadImg) {
                    this.leadImg = leadImg;
                }

                public String getLeadUrl() {
                    return leadUrl;
                }

                public void setLeadUrl(String leadUrl) {
                    this.leadUrl = leadUrl;
                }
            }

            public static class VideoBean {
                /**
                 * count : 51
                 * hightUrl : https://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4
                 * img : http://img5.mtime.cn/mg/2019/03/14/223956.68342218_235X132X4.jpg
                 * title : 复仇者联盟4 剧场版中文预告
                 * url : https://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995_480.mp4
                 * videoId : 74055
                 * videoSourceType : 1
                 */

                private int count;
                private String hightUrl;
                private String img;
                private String title;
                private String url;
                private int videoId;
                private int videoSourceType;

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public String getHightUrl() {
                    return hightUrl;
                }

                public void setHightUrl(String hightUrl) {
                    this.hightUrl = hightUrl;
                }

                public String getImg() {
                    if (!TextUtils.isEmpty(img)) {
                        if (img.contains("_")) {
                            int indexOf = img.indexOf("_");
                            int endIndex = 0;
                            if (img.toLowerCase().contains(".jpg")) {
                                endIndex = img.indexOf(".jpg");
                            } else if (img.toLowerCase().contains(".png")) {
                                endIndex = img.indexOf(".png");
                            }
                            if (endIndex == 0) {
                                return img;
                            } else {
                                String substring = img.substring(indexOf, endIndex);
                                return img.replace(substring, "");
                            }
                        } else {
                            return img;
                        }
                    } else {
                        return "";
                    }
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getVideoId() {
                    return videoId;
                }

                public void setVideoId(int videoId) {
                    this.videoId = videoId;
                }

                public int getVideoSourceType() {
                    return videoSourceType;
                }

                public void setVideoSourceType(int videoSourceType) {
                    this.videoSourceType = videoSourceType;
                }
            }


        }

        public static class BoxOfficeBean {

            private int movieId;
            private int ranking;
            private long todayBox;
            private String todayBoxDes;
            private String todayBoxDesUnit;
            private long totalBox;
            private String totalBoxDes;
            private String totalBoxUnit;

            public int getMovieId() {
                return movieId;
            }

            public void setMovieId(int movieId) {
                this.movieId = movieId;
            }

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
            }

            public long getTodayBox() {
                return todayBox;
            }

            public void setTodayBox(long todayBox) {
                this.todayBox = todayBox;
            }

            public String getTodayBoxDes() {
                return todayBoxDes;
            }

            public void setTodayBoxDes(String todayBoxDes) {
                this.todayBoxDes = todayBoxDes;
            }

            public String getTodayBoxDesUnit() {
                return todayBoxDesUnit;
            }

            public void setTodayBoxDesUnit(String todayBoxDesUnit) {
                this.todayBoxDesUnit = todayBoxDesUnit;
            }

            public long getTotalBox() {
                return totalBox;
            }

            public void setTotalBox(long totalBox) {
                this.totalBox = totalBox;
            }

            public String getTotalBoxDes() {
                return totalBoxDes;
            }

            public void setTotalBoxDes(String totalBoxDes) {
                this.totalBoxDes = totalBoxDes;
            }

            public String getTotalBoxUnit() {
                return totalBoxUnit;
            }

            public void setTotalBoxUnit(String totalBoxUnit) {
                this.totalBoxUnit = totalBoxUnit;
            }
        }


        public static class RelatedBean {

            private int goodsCount;
            private String relatedUrl;
            private int type;
            private List<GoodsListBean> goodsList;

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public String getRelatedUrl() {
                return relatedUrl;
            }

            public void setRelatedUrl(String relatedUrl) {
                this.relatedUrl = relatedUrl;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<GoodsListBean> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<GoodsListBean> goodsList) {
                this.goodsList = goodsList;
            }

            public static class GoodsListBean {
                /**
                 * background :
                 * goodsId : 107042
                 * goodsTip : 自营
                 * goodsUrl : https://mall-wv.mtime.cn/#!/commerce/item/107042/
                 * iconText :
                 * image : http://img5.mtime.cn/mg/2018/04/18/184415.72879699_294X294X4.jpg
                 * longName : 复仇者联盟3 经典漫画复古马克杯
                 * marketPrice : 0
                 * marketPriceFormat : 0.00
                 * minSalePrice : 6900
                 * minSalePriceFormat : 69.00
                 * name : 复联3 经典漫画复古马克杯
                 */

                private String background;
                private int goodsId;
                private String goodsTip;
                private String goodsUrl;
                private String iconText;
                private String image;
                private String longName;
                private String marketPriceFormat;
                private String minSalePriceFormat;
                private String name;

                public String getBackground() {
                    return background;
                }

                public void setBackground(String background) {
                    this.background = background;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public String getGoodsTip() {
                    return goodsTip;
                }

                public void setGoodsTip(String goodsTip) {
                    this.goodsTip = goodsTip;
                }

                public String getGoodsUrl() {
                    return goodsUrl;
                }

                public void setGoodsUrl(String goodsUrl) {
                    this.goodsUrl = goodsUrl;
                }

                public String getIconText() {
                    return iconText;
                }

                public void setIconText(String iconText) {
                    this.iconText = iconText;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getLongName() {
                    return longName;
                }

                public void setLongName(String longName) {
                    this.longName = longName;
                }


                public String getMarketPriceFormat() {
                    return marketPriceFormat;
                }

                public void setMarketPriceFormat(String marketPriceFormat) {
                    this.marketPriceFormat = marketPriceFormat;
                }

                public String getMinSalePriceFormat() {
                    return minSalePriceFormat;
                }

                public void setMinSalePriceFormat(String minSalePriceFormat) {
                    this.minSalePriceFormat = minSalePriceFormat;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }

    public static class ActorsBean {

        private String img;
        private String name;
        private String nameEn;
        private String roleImg;
        private String roleName;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getRoleImg() {
            return roleImg;
        }

        public void setRoleImg(String roleImg) {
            this.roleImg = roleImg;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }

    public static class ImageListBean {

        private int imgId;
        private String imgUrl;

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
