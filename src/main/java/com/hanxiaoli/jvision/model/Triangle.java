package com.hanxiaoli.jvision.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.cloud.vision.v1.Vertex;
import com.google.cloud.vision.v1.Vertex.Builder;

public class Triangle {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<Double, Double> sinMap;

	private Vertex pointLeft;

	private Vertex pointRight;

	private double hypotenuse;

	private double opposite;

	private double adjacent;

	public Triangle(Vertex pointLeft, Vertex pointRight) {
		initSinMap();
		this.pointLeft = pointLeft;
		this.pointRight = pointRight;
		this.adjacent = pointRight.getX() - pointLeft.getX();
		this.opposite = pointRight.getY() - pointLeft.getY();
		this.hypotenuse = Math.sqrt(this.adjacent * this.adjacent + this.opposite * this.opposite);
	}

	public Triangle(double adjacent, double opposite, Vertex pointLeft, Vertex pointRight) {
		initSinMap();
		this.adjacent = adjacent;
		this.opposite = opposite;

		if (null == pointLeft && null != pointRight) {
			Builder builder = Vertex.newBuilder();
			builder.setX((int) (pointRight.getX() - adjacent));
			builder.setY((int) (pointRight.getY() - opposite));

			this.pointLeft = builder.build();
			this.pointRight = pointRight;
		} else if (null != pointLeft && null == pointRight) {
			Builder builder = Vertex.newBuilder();
			builder.setX((int) (pointLeft.getX() + adjacent));
			builder.setY((int) (pointLeft.getY() + opposite));

			this.pointLeft = pointLeft;
			this.pointRight = builder.build();
		} else {
			logger.error("create triangle with line was wrong");
		}
	}

	public double getDegree(double sin) {
		double startIndex = Double.NaN;
		double endIndex = Double.NaN;
		if (0 < this.adjacent && 0 <= this.opposite) {
			startIndex = 0;
			endIndex = 90;
		} else if (0 >= this.adjacent && 0 < this.opposite) {
			startIndex = 90;
			endIndex = 180;
		} else if (0 > this.adjacent && 0 >= this.opposite) {
			startIndex = 180;
			endIndex = 270;
		} else {
			startIndex = 270;
			endIndex = 360;
		}

		double index = Double.NaN;
		double closest = Double.NaN;
		for (double i = startIndex; i <= endIndex; i++) {
			if (Double.isNaN(closest) || Math.abs(sin - closest) > Math.abs(this.sinMap.get(i) - sin)) {
				index = i;
				closest = this.sinMap.get(i);
			}
		}

		logger.info("block degree: " + index);
		return index;
	}

	public double getSin() {
		return this.opposite / this.hypotenuse;
	}

	public double getCos() {
		return this.adjacent / this.hypotenuse;
	}

	public double getTan() {
		return this.opposite / this.adjacent;
	}

	public double getCot() {
		return this.adjacent / this.opposite;
	}

	public Vertex getPointLeft() {
		return pointLeft;
	}

	public void setPointLeft(Vertex pointLeft) {
		this.pointLeft = pointLeft;
	}

	public Vertex getPointRight() {
		return pointRight;
	}

	public void setPointRight(Vertex pointRight) {
		this.pointRight = pointRight;
	}

	public double getHypotenuse() {
		return hypotenuse;
	}

	public void setHypotenuse(double hypotenuse) {
		this.hypotenuse = hypotenuse;
	}

	public double getOpposite() {
		return opposite;
	}

	public void setOpposite(double opposite) {
		this.opposite = opposite;
	}

	public double getAdjacent() {
		return adjacent;
	}

	public void setAdjacent(double adjacent) {
		this.adjacent = adjacent;
	}

	public Map<Double, Double> getSinMap() {
		return sinMap;
	}

	public void setSinMap(Map<Double, Double> sinMap) {
		this.sinMap = sinMap;
	}

	public void initSinMap() {
		Map<Double, Double> sinMap = new HashMap<Double, Double>();
		sinMap.put(0.0, 0.0);
		sinMap.put(1.0, 0.017452);
		sinMap.put(2.0, 0.034899);
		sinMap.put(3.0, 0.052336);
		sinMap.put(4.0, 0.069756);
		sinMap.put(5.0, 0.087156);
		sinMap.put(6.0, 0.104528);
		sinMap.put(7.0, 0.121869);
		sinMap.put(8.0, 0.139173);
		sinMap.put(9.0, 0.156434);
		sinMap.put(10.0, 0.173648);
		sinMap.put(11.0, 0.190809);
		sinMap.put(12.0, 0.207912);
		sinMap.put(13.0, 0.224951);
		sinMap.put(14.0, 0.241922);
		sinMap.put(15.0, 0.258819);
		sinMap.put(16.0, 0.275637);
		sinMap.put(17.0, 0.292372);
		sinMap.put(18.0, 0.309017);
		sinMap.put(19.0, 0.325568);
		sinMap.put(20.0, 0.34202);
		sinMap.put(21.0, 0.358368);
		sinMap.put(22.0, 0.374607);
		sinMap.put(23.0, 0.390731);
		sinMap.put(24.0, 0.406737);
		sinMap.put(25.0, 0.422618);
		sinMap.put(26.0, 0.438371);
		sinMap.put(27.0, 0.45399);
		sinMap.put(28.0, 0.469472);
		sinMap.put(29.0, 0.48481);
		sinMap.put(30.0, 0.5);
		sinMap.put(31.0, 0.515038);
		sinMap.put(32.0, 0.529919);
		sinMap.put(33.0, 0.544639);
		sinMap.put(34.0, 0.559193);
		sinMap.put(35.0, 0.573576);
		sinMap.put(36.0, 0.587785);
		sinMap.put(37.0, 0.601815);
		sinMap.put(38.0, 0.615661);
		sinMap.put(39.0, 0.62932);
		sinMap.put(40.0, 0.642788);
		sinMap.put(41.0, 0.656059);
		sinMap.put(42.0, 0.669131);
		sinMap.put(43.0, 0.681998);
		sinMap.put(44.0, 0.694658);
		sinMap.put(45.0, 0.707107);
		sinMap.put(46.0, 0.71934);
		sinMap.put(47.0, 0.731354);
		sinMap.put(48.0, 0.743145);
		sinMap.put(49.0, 0.75471);
		sinMap.put(50.0, 0.766044);
		sinMap.put(51.0, 0.777146);
		sinMap.put(52.0, 0.788011);
		sinMap.put(53.0, 0.798636);
		sinMap.put(54.0, 0.809017);
		sinMap.put(55.0, 0.819152);
		sinMap.put(56.0, 0.829038);
		sinMap.put(57.0, 0.838671);
		sinMap.put(58.0, 0.848048);
		sinMap.put(59.0, 0.857167);
		sinMap.put(60.0, 0.866025);
		sinMap.put(61.0, 0.87462);
		sinMap.put(62.0, 0.882948);
		sinMap.put(63.0, 0.891007);
		sinMap.put(64.0, 0.898794);
		sinMap.put(65.0, 0.906308);
		sinMap.put(66.0, 0.913545);
		sinMap.put(67.0, 0.920505);
		sinMap.put(68.0, 0.927184);
		sinMap.put(69.0, 0.93358);
		sinMap.put(70.0, 0.939693);
		sinMap.put(71.0, 0.945519);
		sinMap.put(72.0, 0.951057);
		sinMap.put(73.0, 0.956305);
		sinMap.put(74.0, 0.961262);
		sinMap.put(75.0, 0.965926);
		sinMap.put(76.0, 0.970296);
		sinMap.put(77.0, 0.97437);
		sinMap.put(78.0, 0.978148);
		sinMap.put(79.0, 0.981627);
		sinMap.put(80.0, 0.984808);
		sinMap.put(81.0, 0.987688);
		sinMap.put(82.0, 0.990268);
		sinMap.put(83.0, 0.992546);
		sinMap.put(84.0, 0.994522);
		sinMap.put(85.0, 0.996195);
		sinMap.put(86.0, 0.997564);
		sinMap.put(87.0, 0.99863);
		sinMap.put(88.0, 0.999391);
		sinMap.put(89.0, 0.999848);
		sinMap.put(90.0, 1.0);
		sinMap.put(91.0, 0.999848);
		sinMap.put(92.0, 0.999391);
		sinMap.put(93.0, 0.99863);
		sinMap.put(94.0, 0.997564);
		sinMap.put(95.0, 0.996195);
		sinMap.put(96.0, 0.994522);
		sinMap.put(97.0, 0.992546);
		sinMap.put(98.0, 0.990268);
		sinMap.put(99.0, 0.987688);
		sinMap.put(100.0, 0.984808);
		sinMap.put(101.0, 0.981627);
		sinMap.put(102.0, 0.978148);
		sinMap.put(103.0, 0.97437);
		sinMap.put(104.0, 0.970296);
		sinMap.put(105.0, 0.965926);
		sinMap.put(106.0, 0.961262);
		sinMap.put(107.0, 0.956305);
		sinMap.put(108.0, 0.951057);
		sinMap.put(109.0, 0.945519);
		sinMap.put(110.0, 0.939693);
		sinMap.put(111.0, 0.93358);
		sinMap.put(112.0, 0.927184);
		sinMap.put(113.0, 0.920505);
		sinMap.put(114.0, 0.913545);
		sinMap.put(115.0, 0.906308);
		sinMap.put(116.0, 0.898794);
		sinMap.put(117.0, 0.891007);
		sinMap.put(118.0, 0.882948);
		sinMap.put(119.0, 0.87462);
		sinMap.put(120.0, 0.866025);
		sinMap.put(121.0, 0.857167);
		sinMap.put(122.0, 0.848048);
		sinMap.put(123.0, 0.838671);
		sinMap.put(124.0, 0.829038);
		sinMap.put(125.0, 0.819152);
		sinMap.put(126.0, 0.809017);
		sinMap.put(127.0, 0.798636);
		sinMap.put(128.0, 0.788011);
		sinMap.put(129.0, 0.777146);
		sinMap.put(130.0, 0.766044);
		sinMap.put(131.0, 0.75471);
		sinMap.put(132.0, 0.743145);
		sinMap.put(133.0, 0.731354);
		sinMap.put(134.0, 0.71934);
		sinMap.put(135.0, 0.707107);
		sinMap.put(136.0, 0.694658);
		sinMap.put(137.0, 0.681998);
		sinMap.put(138.0, 0.669131);
		sinMap.put(139.0, 0.656059);
		sinMap.put(140.0, 0.642788);
		sinMap.put(141.0, 0.62932);
		sinMap.put(142.0, 0.615661);
		sinMap.put(143.0, 0.601815);
		sinMap.put(144.0, 0.587785);
		sinMap.put(145.0, 0.573576);
		sinMap.put(146.0, 0.559193);
		sinMap.put(147.0, 0.544639);
		sinMap.put(148.0, 0.529919);
		sinMap.put(149.0, 0.515038);
		sinMap.put(150.0, 0.5);
		sinMap.put(151.0, 0.48481);
		sinMap.put(152.0, 0.469472);
		sinMap.put(153.0, 0.45399);
		sinMap.put(154.0, 0.438371);
		sinMap.put(155.0, 0.422618);
		sinMap.put(156.0, 0.406737);
		sinMap.put(157.0, 0.390731);
		sinMap.put(158.0, 0.374607);
		sinMap.put(159.0, 0.358368);
		sinMap.put(160.0, 0.34202);
		sinMap.put(161.0, 0.325568);
		sinMap.put(162.0, 0.309017);
		sinMap.put(163.0, 0.292372);
		sinMap.put(164.0, 0.275637);
		sinMap.put(165.0, 0.258819);
		sinMap.put(166.0, 0.241922);
		sinMap.put(167.0, 0.224951);
		sinMap.put(168.0, 0.207912);
		sinMap.put(169.0, 0.190809);
		sinMap.put(170.0, 0.173648);
		sinMap.put(171.0, 0.156434);
		sinMap.put(172.0, 0.139173);
		sinMap.put(173.0, 0.121869);
		sinMap.put(174.0, 0.104528);
		sinMap.put(175.0, 0.087156);
		sinMap.put(176.0, 0.069756);
		sinMap.put(177.0, 0.052336);
		sinMap.put(178.0, 0.034899);
		sinMap.put(179.0, 0.017452);
		sinMap.put(180.0, 0.0);
		sinMap.put(181.0, -0.017452);
		sinMap.put(182.0, -0.034899);
		sinMap.put(183.0, -0.052336);
		sinMap.put(184.0, -0.069756);
		sinMap.put(185.0, -0.087156);
		sinMap.put(186.0, -0.104528);
		sinMap.put(187.0, -0.121869);
		sinMap.put(188.0, -0.139173);
		sinMap.put(189.0, -0.156434);
		sinMap.put(190.0, -0.173648);
		sinMap.put(191.0, -0.190809);
		sinMap.put(192.0, -0.207912);
		sinMap.put(193.0, -0.224951);
		sinMap.put(194.0, -0.241922);
		sinMap.put(195.0, -0.258819);
		sinMap.put(196.0, -0.275637);
		sinMap.put(197.0, -0.292372);
		sinMap.put(198.0, -0.309017);
		sinMap.put(199.0, -0.325568);
		sinMap.put(200.0, -0.34202);
		sinMap.put(201.0, -0.358368);
		sinMap.put(202.0, -0.374607);
		sinMap.put(203.0, -0.390731);
		sinMap.put(204.0, -0.406737);
		sinMap.put(205.0, -0.422618);
		sinMap.put(206.0, -0.438371);
		sinMap.put(207.0, -0.45399);
		sinMap.put(208.0, -0.469472);
		sinMap.put(209.0, -0.48481);
		sinMap.put(210.0, -0.5);
		sinMap.put(211.0, -0.515038);
		sinMap.put(212.0, -0.529919);
		sinMap.put(213.0, -0.544639);
		sinMap.put(214.0, -0.559193);
		sinMap.put(215.0, -0.573576);
		sinMap.put(216.0, -0.587785);
		sinMap.put(217.0, -0.601815);
		sinMap.put(218.0, -0.615661);
		sinMap.put(219.0, -0.62932);
		sinMap.put(220.0, -0.642788);
		sinMap.put(221.0, -0.656059);
		sinMap.put(222.0, -0.669131);
		sinMap.put(223.0, -0.681998);
		sinMap.put(224.0, -0.694658);
		sinMap.put(225.0, -0.707107);
		sinMap.put(226.0, -0.71934);
		sinMap.put(227.0, -0.731354);
		sinMap.put(228.0, -0.743145);
		sinMap.put(229.0, -0.75471);
		sinMap.put(230.0, -0.766044);
		sinMap.put(231.0, -0.777146);
		sinMap.put(232.0, -0.788011);
		sinMap.put(233.0, -0.798636);
		sinMap.put(234.0, -0.809017);
		sinMap.put(235.0, -0.819152);
		sinMap.put(236.0, -0.829038);
		sinMap.put(237.0, -0.838671);
		sinMap.put(238.0, -0.848048);
		sinMap.put(239.0, -0.857167);
		sinMap.put(240.0, -0.866025);
		sinMap.put(241.0, -0.87462);
		sinMap.put(242.0, -0.882948);
		sinMap.put(243.0, -0.891007);
		sinMap.put(244.0, -0.898794);
		sinMap.put(245.0, -0.906308);
		sinMap.put(246.0, -0.913545);
		sinMap.put(247.0, -0.920505);
		sinMap.put(248.0, -0.927184);
		sinMap.put(249.0, -0.93358);
		sinMap.put(250.0, -0.939693);
		sinMap.put(251.0, -0.945519);
		sinMap.put(252.0, -0.951057);
		sinMap.put(253.0, -0.956305);
		sinMap.put(254.0, -0.961262);
		sinMap.put(255.0, -0.965926);
		sinMap.put(256.0, -0.970296);
		sinMap.put(257.0, -0.97437);
		sinMap.put(258.0, -0.978148);
		sinMap.put(259.0, -0.981627);
		sinMap.put(260.0, -0.984808);
		sinMap.put(261.0, -0.987688);
		sinMap.put(262.0, -0.990268);
		sinMap.put(263.0, -0.992546);
		sinMap.put(264.0, -0.994522);
		sinMap.put(265.0, -0.996195);
		sinMap.put(266.0, -0.997564);
		sinMap.put(267.0, -0.99863);
		sinMap.put(268.0, -0.999391);
		sinMap.put(269.0, -0.999848);
		sinMap.put(270.0, -1.0);
		sinMap.put(271.0, -0.999848);
		sinMap.put(272.0, -0.999391);
		sinMap.put(273.0, -0.99863);
		sinMap.put(274.0, -0.997564);
		sinMap.put(275.0, -0.996195);
		sinMap.put(276.0, -0.994522);
		sinMap.put(277.0, -0.992546);
		sinMap.put(278.0, -0.990268);
		sinMap.put(279.0, -0.987688);
		sinMap.put(280.0, -0.984808);
		sinMap.put(281.0, -0.981627);
		sinMap.put(282.0, -0.978148);
		sinMap.put(283.0, -0.97437);
		sinMap.put(284.0, -0.970296);
		sinMap.put(285.0, -0.965926);
		sinMap.put(286.0, -0.961262);
		sinMap.put(287.0, -0.956305);
		sinMap.put(288.0, -0.951057);
		sinMap.put(289.0, -0.945519);
		sinMap.put(290.0, -0.939693);
		sinMap.put(291.0, -0.93358);
		sinMap.put(292.0, -0.927184);
		sinMap.put(293.0, -0.920505);
		sinMap.put(294.0, -0.913545);
		sinMap.put(295.0, -0.906308);
		sinMap.put(296.0, -0.898794);
		sinMap.put(297.0, -0.891007);
		sinMap.put(298.0, -0.882948);
		sinMap.put(299.0, -0.87462);
		sinMap.put(300.0, -0.866025);
		sinMap.put(301.0, -0.857167);
		sinMap.put(302.0, -0.848048);
		sinMap.put(303.0, -0.838671);
		sinMap.put(304.0, -0.829038);
		sinMap.put(305.0, -0.819152);
		sinMap.put(306.0, -0.809017);
		sinMap.put(307.0, -0.798636);
		sinMap.put(308.0, -0.788011);
		sinMap.put(309.0, -0.777146);
		sinMap.put(310.0, -0.766044);
		sinMap.put(311.0, -0.75471);
		sinMap.put(312.0, -0.743145);
		sinMap.put(313.0, -0.731354);
		sinMap.put(314.0, -0.71934);
		sinMap.put(315.0, -0.707107);
		sinMap.put(316.0, -0.694658);
		sinMap.put(317.0, -0.681998);
		sinMap.put(318.0, -0.669131);
		sinMap.put(319.0, -0.656059);
		sinMap.put(320.0, -0.642788);
		sinMap.put(321.0, -0.62932);
		sinMap.put(322.0, -0.615661);
		sinMap.put(323.0, -0.601815);
		sinMap.put(324.0, -0.587785);
		sinMap.put(325.0, -0.573576);
		sinMap.put(326.0, -0.559193);
		sinMap.put(327.0, -0.544639);
		sinMap.put(328.0, -0.529919);
		sinMap.put(329.0, -0.515038);
		sinMap.put(330.0, -0.5);
		sinMap.put(331.0, -0.48481);
		sinMap.put(332.0, -0.469472);
		sinMap.put(333.0, -0.45399);
		sinMap.put(334.0, -0.438371);
		sinMap.put(335.0, -0.422618);
		sinMap.put(336.0, -0.406737);
		sinMap.put(337.0, -0.390731);
		sinMap.put(338.0, -0.374607);
		sinMap.put(339.0, -0.358368);
		sinMap.put(340.0, -0.34202);
		sinMap.put(341.0, -0.325568);
		sinMap.put(342.0, -0.309017);
		sinMap.put(343.0, -0.292372);
		sinMap.put(344.0, -0.275637);
		sinMap.put(345.0, -0.258819);
		sinMap.put(346.0, -0.241922);
		sinMap.put(347.0, -0.224951);
		sinMap.put(348.0, -0.207912);
		sinMap.put(349.0, -0.190809);
		sinMap.put(350.0, -0.173648);
		sinMap.put(351.0, -0.156434);
		sinMap.put(352.0, -0.139173);
		sinMap.put(353.0, -0.121869);
		sinMap.put(354.0, -0.104528);
		sinMap.put(355.0, -0.087156);
		sinMap.put(356.0, -0.069756);
		sinMap.put(357.0, -0.052336);
		sinMap.put(358.0, -0.034899);
		sinMap.put(359.0, -0.017452);
		sinMap.put(360.0, 0.0);
		this.sinMap = sinMap;
	}

}
