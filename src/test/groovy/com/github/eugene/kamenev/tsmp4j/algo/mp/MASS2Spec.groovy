package com.github.eugene.kamenev.tsmp4j.algo.mp

import com.github.eugene.kamenev.tsmp4j.BaseSpec
import com.github.eugene.kamenev.tsmp4j.stats.RollingWindowStatistics

class MASS2Spec extends BaseSpec {

    def 'test mass2 distance for query'() {
        given:
        var distOrig = new double[]{2.3668965327355567, 3.4060132019942193, 2.9032584986134187, 0.697189360491946,
                2.564482199238838, 4.173952551042108, 3.641533468524242, 3.5935776279373743, 4.137081417670844,
                4.457607139901744, 4.431555859764974, 3.213841006777569, 0.6047023031090474, 3.1041799031762967,
                4.470441329915717, 4.125780530323814, 3.738677184993776, 3.759127813545457, 4.043050494354343,
                4.786387167956826, 3.8406642856176942, 2.661562742597054, 3.525910756817523, 3.214588771573668,
                1.1673105773258903, 2.9681630725325654, 4.231684110779005, 2.8078035727048456, 6.542950391799256E-7,
                3.1069506036555286, 4.513042682841856, 3.218706435381751, 2.14488563384738, 3.934956034027165,
                4.225083663458599, 3.821761785345017, 3.8462063907011657, 4.406442054759158, 4.790377123441251,
                3.743087824261052, 1.909940763238877, 1.9294311137988711, 3.4722046944838154, 2.3718192858198504,
                3.1911690614398625, 4.05382033055673, 3.3815347090246832, 1.9174055058897412, 3.1006973624748007,
                3.6546095561285057, 2.9117004242206312, 1.6556301961839521, 1.9580427423249531, 3.6387515556921874,
                3.1606124690560313, 0.5679781945823819, 2.6433757557568747, 4.202408197533685, 3.7523411112426395,
                3.551779992997057, 4.496782247434107, 4.468366408151725, 3.4920148506394533, 3.6050715839335767,
                4.3411447021426675, 4.200149269689229, 3.8578490765041473, 4.594175173745556, 3.712677713373182,
                3.2302948287732107, 1.9189154504499335, 1.683469236170608, 3.460319505045696, 4.021019724347921,
                4.0968741684324295, 4.605768013107236, 4.156449059287281, 2.04022853964161, 1.162981698471108,
                4.042829593212975, 4.631619198358055, 3.0044301207350115, 1.6435869891044623, 2.8731404638900333,
                3.8757666746586934, 4.028357105034631, 4.2079409247915605, 4.002642512635227, 4.6636060949775064,
                3.7191735149087055, 2.6233748670246717, 2.8037366924403546, 4.738290579046625, 3.0049182185798626,
                2.435347999888012, 3.1701103260300556, 3.857215751196695, 2.216149228999814, 0.9865620496359858,
                3.234225731274193, 4.87346503179918, 3.5164137887045586, 1.1752178091695555, 3.5783341298193423,
                4.707989622267232, 3.2710825465503457, 2.758679505488824, 4.001399279436573, 4.5007815474411155,
                3.929933232627954, 3.842705957813346, 3.233840801437425, 2.7655709988782506, 2.369719266273785,
                1.2783561136897605, 1.6938320830185023, 4.191468434347509, 4.417641498556896, 3.1458027081346565,
                3.396909990444282, 4.313077021928001, 4.804077187095176, 3.5059890582542703}

        when:
        var ts = new RollingWindowStatistics(query.length, timeSeries.length, false)
        var qts = new RollingWindowStatistics(query.length, query.length, false)
        for (var i = 0; i < timeSeries.length; i++) {
            ts.apply(timeSeries[i])
        }
        for (var i = 0; i < query.length; i++) {
            qts.apply(query[i])
        }
        var query = new DistanceProfileFunction.DistanceProfileQuery(ts, qts, 0, 6)
        var dist = new MASS2().apply(query)

        then:
        equals(dist, distOrig)
    }

}
