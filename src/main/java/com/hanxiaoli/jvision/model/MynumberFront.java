package com.hanxiaoli.jvision.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MynumberFront {

	/**
	 * 正面参照文字列表
	 */
	public List<String> referenceTextFront = Arrays.asList("住", "所", "意", "思", "性", "別", "個", "及", "特");

	/**
	 * 各区域到参照物左上角之间的距离（mm）
	 */
	public Map<String, Map<String, Area>> lengthToReferenceSymbol;

	/**
	 * 参照物与其它比较参照物之间的距离（毫米）
	 */
	public Map<String, Map<String, Map<String, Double>>> comparativeSymbolsDistance;

	public MynumberFront() {
		this.lengthToReferenceSymbol = new HashMap<>();

		// zhu
		Map<String, Area> zhu = new HashMap<>();
		Area cardZhu = new Area();
		cardZhu.setAreaName("card");
		cardZhu.setLeft(3);
		cardZhu.setRight(82.5);
		cardZhu.setUp(11);
		cardZhu.setDown(43);

		Area nameZhu = new Area();
		nameZhu.setAreaName("name");
		nameZhu.setLeft(0);
		nameZhu.setRight(64);
		nameZhu.setUp(11);
		nameZhu.setDown(0);

		Area addressZhu = new Area();
		addressZhu.setAreaName("address");
		addressZhu.setLeft(5);
		addressZhu.setRight(62);
		addressZhu.setUp(2);
		addressZhu.setDown(8);

		Area sexZhu = new Area();
		sexZhu.setAreaName("sex");
		sexZhu.setLeft(-62);
		sexZhu.setRight(80);
		sexZhu.setUp(-1);
		sexZhu.setDown(9);

		Area birthdayZhu = new Area();
		birthdayZhu.setAreaName("birthday");
		birthdayZhu.setLeft(-20);
		birthdayZhu.setRight(52.5);
		birthdayZhu.setUp(-4.5);
		birthdayZhu.setDown(13);

		Area expiryZhu = new Area();
		expiryZhu.setAreaName("expiry");
		expiryZhu.setLeft(-50);
		expiryZhu.setRight(78.5);
		expiryZhu.setUp(-5.5);
		expiryZhu.setDown(13);

		zhu.put(cardZhu.getAreaName(), cardZhu);
		zhu.put(nameZhu.getAreaName(), nameZhu);
		zhu.put(addressZhu.getAreaName(), addressZhu);
		zhu.put(sexZhu.getAreaName(), sexZhu);
		zhu.put(birthdayZhu.getAreaName(), birthdayZhu);
		zhu.put(expiryZhu.getAreaName(), expiryZhu);

		// suo
		Map<String, Area> suo = new HashMap<>();
		Area cardSuo = new Area();
		cardSuo.setAreaName("card");
		cardSuo.setLeft(5);
		cardSuo.setRight(80.5);
		cardSuo.setUp(11);
		cardSuo.setDown(43);

		Area nameSuo = new Area();
		nameSuo.setAreaName("name");
		nameSuo.setLeft(2);
		nameSuo.setRight(62);
		nameSuo.setUp(11);
		nameSuo.setDown(0);

		Area addressSuo = new Area();
		addressSuo.setAreaName("address");
		addressSuo.setLeft(3);
		addressSuo.setRight(64);
		addressSuo.setUp(2);
		addressSuo.setDown(8);

		Area sexSuo = new Area();
		sexSuo.setAreaName("sex");
		sexSuo.setLeft(-60);
		sexSuo.setRight(82);
		sexSuo.setUp(-1);
		sexSuo.setDown(9);

		Area birthdaySuo = new Area();
		birthdaySuo.setAreaName("birthday");
		birthdaySuo.setLeft(-18);
		birthdaySuo.setRight(50);
		birthdaySuo.setUp(-4.5);
		birthdaySuo.setDown(13);

		Area expirySuo = new Area();
		expirySuo.setAreaName("expiry");
		expirySuo.setLeft(-47);
		expirySuo.setRight(80.5);
		expirySuo.setUp(-5.5);
		expirySuo.setDown(13);

		suo.put(cardSuo.getAreaName(), cardSuo);
		suo.put(nameSuo.getAreaName(), nameSuo);
		suo.put(addressSuo.getAreaName(), addressSuo);
		suo.put(sexSuo.getAreaName(), sexSuo);
		suo.put(birthdaySuo.getAreaName(), birthdaySuo);
		suo.put(expirySuo.getAreaName(), expirySuo);

		// yi
		Map<String, Area> yi = new HashMap<>();
		Area cardYi = new Area();
		cardYi.setAreaName("card");
		cardYi.setLeft(38.8);
		cardYi.setRight(46.8);
		cardYi.setUp(44.8);
		cardYi.setDown(8.5);

		Area nameYi = new Area();
		nameYi.setAreaName("name");
		nameYi.setLeft(38.8);
		nameYi.setRight(29);
		nameYi.setUp(44.8);
		nameYi.setDown(-35.5);

		Area addressYi = new Area();
		addressYi.setAreaName("address");
		addressYi.setLeft(38.8);
		addressYi.setRight(29);
		addressYi.setUp(38.5);
		addressYi.setDown(-27);

		Area sexYi = new Area();
		sexYi.setAreaName("sex");
		sexYi.setLeft(-28);
		sexYi.setRight(46.8);
		sexYi.setUp(33);
		sexYi.setDown(-26.5);

		Area birthdayYi = new Area();
		birthdayYi.setAreaName("birthday");
		birthdayYi.setLeft(13.5);
		birthdayYi.setRight(16);
		birthdayYi.setUp(31.5);
		birthdayYi.setDown(-22.5);

		Area expiryYi = new Area();
		expiryYi.setAreaName("expiry");
		expiryYi.setLeft(-12.5);
		expiryYi.setRight(47);
		expiryYi.setUp(28.5);
		expiryYi.setDown(-22.5);

		yi.put(cardYi.getAreaName(), cardYi);
		yi.put(nameYi.getAreaName(), nameYi);
		yi.put(addressYi.getAreaName(), addressYi);
		yi.put(sexYi.getAreaName(), sexSuo);
		yi.put(birthdayYi.getAreaName(), birthdayYi);
		yi.put(expiryYi.getAreaName(), expiryYi);

		// si
		Map<String, Area> si = new HashMap<>();
		Area cardSi = new Area();
		cardSi.setAreaName("card");
		cardSi.setLeft(40.3);
		cardSi.setRight(45.3);
		cardSi.setUp(44.8);
		cardSi.setDown(8.5);

		Area nameSi = new Area();
		nameSi.setAreaName("name");
		nameSi.setLeft(40.3);
		nameSi.setRight(27.5);
		nameSi.setUp(44.8);
		nameSi.setDown(-35.5);

		Area addressSi = new Area();
		addressSi.setAreaName("address");
		addressSi.setLeft(40.3);
		addressSi.setRight(27.5);
		addressSi.setUp(38.5);
		addressSi.setDown(-27);

		Area sexSi = new Area();
		sexSi.setAreaName("sex");
		sexSi.setLeft(-26.5);
		sexSi.setRight(45.3);
		sexSi.setUp(33);
		sexSi.setDown(-26.5);

		Area birthdaySi = new Area();
		birthdaySi.setAreaName("birthday");
		birthdaySi.setLeft(15);
		birthdaySi.setRight(14.5);
		birthdaySi.setUp(31.5);
		birthdaySi.setDown(-22.5);

		Area expirySi = new Area();
		expirySi.setAreaName("expiry");
		expirySi.setLeft(-11);
		expirySi.setRight(48.5);
		expirySi.setUp(28.5);
		expirySi.setDown(-22.5);

		si.put(cardSi.getAreaName(), cardSi);
		si.put(nameSi.getAreaName(), nameSi);
		si.put(addressSi.getAreaName(), addressSi);
		si.put(sexSi.getAreaName(), sexSi);
		si.put(birthdaySi.getAreaName(), birthdaySi);
		si.put(expirySi.getAreaName(), expirySi);

		// xing
		Map<String, Area> xing = new HashMap<>();
		Area cardXing = new Area();
		cardXing.setAreaName("card");
		cardXing.setLeft(72.8);
		cardXing.setRight(13);
		cardXing.setUp(14.5);
		cardXing.setDown(40);

		Area nameXing = new Area();
		nameXing.setAreaName("name");
		nameXing.setLeft(72.8);
		nameXing.setRight(-6);
		nameXing.setUp(14.5);
		nameXing.setDown(-4);

		Area addressXing = new Area();
		addressXing.setAreaName("address");
		addressXing.setLeft(72.8);
		addressXing.setRight(-6);
		addressXing.setUp(8.5);
		addressXing.setDown(4);

		Area sexXing = new Area();
		sexXing.setAreaName("sex");
		sexXing.setLeft(6);
		sexXing.setRight(13);
		sexXing.setUp(3);
		sexXing.setDown(4);

		Area birthdayXing = new Area();
		birthdayXing.setAreaName("birthday");
		birthdayXing.setLeft(48);
		birthdayXing.setRight(-18);
		birthdayXing.setUp(-2);
		birthdayXing.setDown(8);

		Area expiryXing = new Area();
		expiryXing.setAreaName("expiry");
		expiryXing.setLeft(21.5);
		expiryXing.setRight(13);
		expiryXing.setUp(-2);
		expiryXing.setDown(8);

		// bie
		Map<String, Area> bie = new HashMap<>();
		Area cardBie = new Area();
		cardBie.setAreaName("card");
		cardBie.setLeft(74.8);
		cardBie.setRight(11);
		cardBie.setUp(14.5);
		cardBie.setDown(40);

		Area nameBie = new Area();
		nameBie.setAreaName("name");
		nameBie.setLeft(74.8);
		nameBie.setRight(-8);
		nameBie.setUp(14.5);
		nameBie.setDown(-4);

		Area addressBie = new Area();
		addressBie.setAreaName("address");
		addressBie.setLeft(74.8);
		addressBie.setRight(-8);
		addressBie.setUp(8.5);
		addressBie.setDown(4);

		Area sexBie = new Area();
		sexBie.setAreaName("sex");
		sexBie.setLeft(8);
		sexBie.setRight(11);
		sexBie.setUp(3);
		sexBie.setDown(4);

		Area birthdayBie = new Area();
		birthdayBie.setAreaName("birthday");
		birthdayBie.setLeft(50);
		birthdayBie.setRight(-20);
		birthdayBie.setUp(-2);
		birthdayBie.setDown(8);

		Area expiryBie = new Area();
		expiryBie.setAreaName("expiry");
		expiryBie.setLeft(23.5);
		expiryBie.setRight(11);
		expiryBie.setUp(-2);
		expiryBie.setDown(8);

		bie.put(cardBie.getAreaName(), cardBie);
		bie.put(nameBie.getAreaName(), nameBie);
		bie.put(addressBie.getAreaName(), addressBie);
		bie.put(sexBie.getAreaName(), sexBie);
		bie.put(birthdayBie.getAreaName(), birthdayBie);
		bie.put(expiryBie.getAreaName(), expiryBie);

		// ge
		Map<String, Area> ge = new HashMap<>();
		Area cardGe = new Area();
		cardGe.setAreaName("card");
		cardGe.setLeft(74);
		cardGe.setRight(11.5);
		cardGe.setUp(9);
		cardGe.setDown(46);

		Area nameGe = new Area();
		nameGe.setAreaName("name");
		nameGe.setLeft(74);
		nameGe.setRight(-7);
		nameGe.setUp(8);
		nameGe.setDown(2.5);

		Area addressGe = new Area();
		addressGe.setAreaName("address");
		addressGe.setLeft(74);
		addressGe.setRight(-7);
		addressGe.setUp(1.5);
		addressGe.setDown(10);

		Area sexGe = new Area();
		sexGe.setAreaName("sex");
		sexGe.setLeft(7);
		sexGe.setRight(11);
		sexGe.setUp(-4);
		sexGe.setDown(10.5);

		Area birthdayGe = new Area();
		birthdayGe.setAreaName("birthday");
		birthdayGe.setLeft(48);
		birthdayGe.setRight(-19.5);
		birthdayGe.setUp(-5);
		birthdayGe.setDown(16);

		Area expiryGe = new Area();
		expiryGe.setAreaName("expiry");
		expiryGe.setLeft(22);
		expiryGe.setRight(11.5);
		expiryGe.setUp(-8.5);
		expiryGe.setDown(16);

		ge.put(cardGe.getAreaName(), cardGe);
		ge.put(nameGe.getAreaName(), nameGe);
		ge.put(addressGe.getAreaName(), addressGe);
		ge.put(sexGe.getAreaName(), sexGe);
		ge.put(birthdayGe.getAreaName(), birthdayGe);
		ge.put(expiryGe.getAreaName(), expiryGe);

		// ji
		Map<String, Area> ji = new HashMap<>();
		Area cardJi = new Area();
		cardJi.setAreaName("card");
		cardJi.setLeft(48);
		cardJi.setRight(37.5);
		cardJi.setUp(45);
		cardJi.setDown(9);

		Area nameJi = new Area();
		nameJi.setAreaName("name");
		nameJi.setLeft(48);
		nameJi.setRight(19);
		nameJi.setUp(45);
		nameJi.setDown(-35);

		Area addressJi = new Area();
		addressJi.setAreaName("address");
		addressJi.setLeft(48);
		addressJi.setRight(19);
		addressJi.setUp(39);
		addressJi.setDown(-27);

		Area sexJi = new Area();
		sexJi.setAreaName("sex");
		sexJi.setLeft(-18.5);
		sexJi.setRight(37.5);
		sexJi.setUp(33.5);
		sexJi.setDown(-26);

		Area birthdayJi = new Area();
		birthdayJi.setAreaName("birthday");
		birthdayJi.setLeft(23);
		birthdayJi.setRight(7.5);
		birthdayJi.setUp(32);
		birthdayJi.setDown(-22.5);

		Area expiryJi = new Area();
		expiryJi.setAreaName("expiry");
		expiryJi.setLeft(-3.5);
		expiryJi.setRight(37.5);
		expiryJi.setUp(28.5);
		expiryJi.setDown(-22);

		ji.put(cardJi.getAreaName(), cardJi);
		ji.put(nameJi.getAreaName(), nameJi);
		ji.put(addressJi.getAreaName(), addressJi);
		ji.put(sexJi.getAreaName(), sexJi);
		ji.put(birthdayJi.getAreaName(), birthdayJi);
		ji.put(expiryJi.getAreaName(), expiryJi);

		// te
		Map<String, Area> te = new HashMap<>();
		Area cardTe = new Area();
		cardTe.setAreaName("card");
		cardTe.setLeft(32.9);
		cardTe.setRight(53);
		cardTe.setUp(50);
		cardTe.setDown(4);

		Area nameTe = new Area();
		nameTe.setAreaName("name");
		nameTe.setLeft(32.9);
		nameTe.setRight(33.7);
		nameTe.setUp(50);
		nameTe.setDown(-39.5);

		Area addressTe = new Area();
		addressTe.setAreaName("address");
		addressTe.setLeft(32.9);
		addressTe.setRight(33.7);
		addressTe.setUp(44);
		addressTe.setDown(-31.9);

		Area sexTe = new Area();
		sexTe.setAreaName("sex");
		sexTe.setLeft(-33.7);
		sexTe.setRight(53);
		sexTe.setUp(37.8);
		sexTe.setDown(-31);

		Area birthdayTe = new Area();
		birthdayTe.setAreaName("birthday");
		birthdayTe.setLeft(7.5);
		birthdayTe.setRight(22.2);
		birthdayTe.setUp(37.5);
		birthdayTe.setDown(-27.5);

		Area expiryTe = new Area();
		expiryTe.setAreaName("expiry");
		expiryTe.setLeft(-19);
		expiryTe.setRight(53);
		expiryTe.setUp(34);
		expiryTe.setDown(-26);

		te.put(cardTe.getAreaName(), cardTe);
		te.put(nameTe.getAreaName(), nameTe);
		te.put(addressTe.getAreaName(), addressTe);
		te.put(sexTe.getAreaName(), sexTe);
		te.put(birthdayTe.getAreaName(), birthdayTe);
		te.put(expiryTe.getAreaName(), expiryTe);

		this.lengthToReferenceSymbol.put("住", zhu);
		this.lengthToReferenceSymbol.put("所", suo);
		this.lengthToReferenceSymbol.put("意", yi);
		this.lengthToReferenceSymbol.put("思", si);
		this.lengthToReferenceSymbol.put("性", xing);
		this.lengthToReferenceSymbol.put("別", bie);
		this.lengthToReferenceSymbol.put("個", ge);
		this.lengthToReferenceSymbol.put("及", ji);
		this.lengthToReferenceSymbol.put("特", te);

		//////////////////////////////////////////////////////////////////////////
		this.comparativeSymbolsDistance = new HashMap<>();

		// zhu
		Map<String, Double> heightZhu = new HashMap<>();
		Map<String, Double> widthZhu = new HashMap<>();
		widthZhu.put("別", 72.0);
		widthZhu.put("個", 71.5);
		widthZhu.put("性", 70.5);
		widthZhu.put("及", 57.0);
		widthZhu.put("思", 51.0);
		widthZhu.put("意", 50.0);
		widthZhu.put("特", 50.0);

		Map<String, Map<String, Double>> comparativeZhu = new HashMap<>();
		comparativeZhu.put("width", widthZhu);
		comparativeZhu.put("height", heightZhu);

		// suo
		Map<String, Double> heightSuo = new HashMap<>();
		Map<String, Double> widthSuo = new HashMap<>();
		widthSuo.put("別", 70.0);
		widthSuo.put("個", 69.0);
		widthSuo.put("性", 68.0);
		widthSuo.put("及", 57.0);
		widthSuo.put("思", 48.0);
		widthSuo.put("特", 48.0);
		widthSuo.put("意", 49.0);

		Map<String, Map<String, Double>> comparativeSuo = new HashMap<>();
		comparativeSuo.put("width", widthSuo);
		comparativeSuo.put("height", heightSuo);

		// yi
		Map<String, Double> heightYi = new HashMap<>();
		Map<String, Double> widthYi = new HashMap<>();

		widthYi.put("個", 51.0);
		widthYi.put("住", 49.5);
		widthYi.put("所", 48.0);
		widthYi.put("別", 47.0);
		widthYi.put("性", 46.0);
		widthYi.put("及", 9.25);

		Map<String, Map<String, Double>> comparativeYi = new HashMap<>();
		comparativeYi.put("width", widthYi);
		comparativeYi.put("height", heightYi);

		// si
		Map<String, Double> heightSi = new HashMap<>();
		Map<String, Double> widthSi = new HashMap<>();

		widthSi.put("住", 50.0);
		widthSi.put("個", 50.0);
		widthSi.put("所", 49.0);
		widthSi.put("別", 46.0);
		widthSi.put("性", 45.0);
		widthSi.put("及", 7.9);

		Map<String, Map<String, Double>> comparativeSi = new HashMap<>();
		comparativeSi.put("width", widthSi);
		comparativeSi.put("height", heightSi);

		// xing
		Map<String, Double> heightXing = new HashMap<>();
		Map<String, Double> widthXing = new HashMap<>();

		widthXing.put("住", 70.5);
		widthXing.put("所", 68.0);
		widthXing.put("特", 54.0);
		widthXing.put("意", 46.0);
		widthXing.put("思", 45.0);
		widthXing.put("及", 39.5);

		Map<String, Map<String, Double>> comparativeXing = new HashMap<>();
		comparativeXing.put("width", widthXing);
		comparativeXing.put("height", heightXing);

		// bie
		Map<String, Double> heightBie = new HashMap<>();
		Map<String, Double> widthBie = new HashMap<>();

		widthBie.put("住", 72.0);
		widthBie.put("所", 70.0);
		widthBie.put("特", 55.0);
		widthBie.put("意", 47.0);
		widthBie.put("思", 46.0);
		widthBie.put("及", 40.5);

		Map<String, Map<String, Double>> comparativeBie = new HashMap<>();
		comparativeBie.put("width", widthBie);
		comparativeBie.put("height", heightBie);

		// ge
		Map<String, Double> heightGe = new HashMap<>();
		Map<String, Double> widthGe = new HashMap<>();

		widthGe.put("住", 71.5);
		widthGe.put("所", 69.0);
		widthGe.put("特", 59.0);
		widthGe.put("意", 51.0);
		widthGe.put("思", 50.0);
		widthGe.put("及", 45.0);

		Map<String, Map<String, Double>> comparativeGe = new HashMap<>();
		comparativeGe.put("width", widthGe);
		comparativeGe.put("height", heightGe);

		// ji
		Map<String, Double> heightJi = new HashMap<>();
		Map<String, Double> widthJi = new HashMap<>();

		widthJi.put("住", 57.0);
		widthJi.put("所", 55.0);
		widthJi.put("個", 45.0);
		widthJi.put("別", 40.5);
		widthJi.put("性", 39.7);
		widthJi.put("特", 16.2);
		widthJi.put("意", 9.25);
		widthJi.put("思", 7.9);

		Map<String, Map<String, Double>> comparativeJi = new HashMap<>();
		comparativeJi.put("width", widthJi);
		comparativeJi.put("height", heightJi);

		// te
		Map<String, Double> heightTe = new HashMap<>();
		Map<String, Double> widthTe = new HashMap<>();

		widthTe.put("個", 59.0);
		widthTe.put("別", 55.0);
		widthTe.put("性", 54.0);
		widthTe.put("住", 50.0);
		widthTe.put("所", 48.0);
		widthTe.put("及", 16.1);

		Map<String, Map<String, Double>> comparativeTe = new HashMap<>();
		comparativeTe.put("width", widthTe);
		comparativeTe.put("height", heightTe);

		this.comparativeSymbolsDistance.put("住", comparativeZhu);
		this.comparativeSymbolsDistance.put("所", comparativeSuo);
		this.comparativeSymbolsDistance.put("意", comparativeYi);
		this.comparativeSymbolsDistance.put("思", comparativeSi);
		this.comparativeSymbolsDistance.put("性", comparativeXing);
		this.comparativeSymbolsDistance.put("別", comparativeBie);
		this.comparativeSymbolsDistance.put("個", comparativeGe);
		this.comparativeSymbolsDistance.put("及", comparativeJi);
		this.comparativeSymbolsDistance.put("特", comparativeTe);
	}

	public List<String> getReferenceTextFront() {
		return referenceTextFront;
	}

	public void setReferenceTextFront(List<String> referenceTextFront) {
		this.referenceTextFront = referenceTextFront;
	}

	public Map<String, Map<String, Area>> getLengthToReferenceSymbol() {
		return lengthToReferenceSymbol;
	}

	public void setLengthToReferenceSymbol(Map<String, Map<String, Area>> lengthToReferenceSymbol) {
		this.lengthToReferenceSymbol = lengthToReferenceSymbol;
	}

	public Map<String, Map<String, Map<String, Double>>> getComparativeSymbolsDistance() {
		return comparativeSymbolsDistance;
	}

	public void setComparativeSymbolsDistance(
			Map<String, Map<String, Map<String, Double>>> comparativeSymbolsDistance) {
		this.comparativeSymbolsDistance = comparativeSymbolsDistance;
	}

}
