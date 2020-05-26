var DATA = require('../../data.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 搜索框内容
    searchInputVal:"",
    banners:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    // 获取轮播信息
    this.initBanners();
    // 获取打折商品信息

    // 获取热销商品信息
    
    // 获取新品信息
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },

  /**
   * 定义获取轮播信息方法
   */
  async initBanners() {
    this.setData({banners: DATA.banners});
  }
})