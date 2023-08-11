package com.github.eugene.kamenev.tsmp4j.algo.pnp

import com.github.eugene.kamenev.tsmp4j.BaseSpec
import com.github.eugene.kamenev.tsmp4j.utils.Util
import com.github.eugene.kamenev.tsmp4j.algo.pmp.SKIMP

class SKIMPSpec extends BaseSpec {

    def 'test skimp no cross correlation'() {
        given:
        var distances = new double[][]{
                new double[]{0.17215017080936074, 0.07320653593174684, 0.2287111247155683, 0.13232167109664292, 0.17005796535965392, 0.4628153540780668, 0.5515412225236123, 0.36036267420807644, 0.19962856532838197, 0.2847986904023008, 0.21803861708054523, 0.07691821713604813, 0.1426268951479611, 0.28252182790871594, 0.22319541056269462, 0.3470021768774117, 0.07079096842093337, 0.08031638278106316, 0.09413230608192982, 0.19962856532838197, 0.32798076674229437, 0.26238026741688425, 0.17385073883821603, 0.042076950568606566, 0.1426268951479611, 0.30593935477515755, 0.5673733231630439, 0.3489354417567462, 0.3449596272187649, 0.31542452130246623, 0.21803861708054523, 0.042076950568606566, 0.46484606190674255, 0.34493538796775736, 0.16332969886918708, 0.6146489444399774, 0.2879887915655875, 0.20645590182470389, 0.08031638278106316, 0.43647708295471727, 0.11868801890697304, 0.17215017080936074, 0.1793380796992417, 0.4433726788845626, 0.6316111814348627, 0.08689083302444611, 0.1521681867809119, 0.11868801890697304, 0.5017936432277585, 0.42074119330671533, 0.2287111247155683, 0.13232167109664292, 0.2849691155970107, 0.9280701623812095, 0.1793380796992417, 0.17518344233132835, 0.17005796535965392, 0.3142141209634956, 0.45959154309281136, 0.36036267420807644, 0.32904867644480057, 0.1726022421374939, 0.8196283805630777, 0.4781466381549412, 0.09384291673615903, 0.17604025522408884, 0.23526223910146082, 0.3555975410926858, 0.5998388819360491, 0.5255583563964052, 0.2895042658913823, 0.36864257205107326, 0.3798533998054062, 0.3300251088521875, 0.23526223910146082, 0.09384291673615903, 0.32798076674229437, 0.4126476068454184, 0.4349566568402656, 0.07079096842093337, 0.5673733231630439, 0.5778099619747114, 0.3449596272187649, 0.5017936432277585, 0.09413230608192982, 0.1443605301542664, 0.20645590182470389, 0.17750095695269305, 0.2684060725043942, 0.42074119330671533, 0.9593505813219867, 0.30593935477515755, 0.17604025522408884, 0.1521681867809119, 0.26238026741688425, 0.17385073883821603, 0.8525680661805823, 0.07320653593174684, 0.17544089924087194, 0.28252182790871594, 0.22319541056269462, 0.5439460252040023, 0.3769258135514627, 0.4205251918937891, 0.16332969886918708, 0.07691821713604813, 0.46484606190674255, 0.21695530654377876, 0.13808324065380093, 0.3470021768774117, 0.08689083302444611, 0.13903100903509366, 0.15240006822691324, 0.41736652035362, 0.20608652951814246, 0.4349566568402656, 0.5359419807368855, 0.35980312466673525, 0.13903100903509366, 0.6700860221662872, 0.0956209200623947, 0.40269628066098956, 0.11700903276572316, 0.21831985528766948, 0.31542452130246623},
                new double[]{0.3474446842466427, 0.43754870424409603, 0.22100635228718815, 0.2685535277782793, 0.4614139617162376, 0.6586695960953836, 0.3193337488194099, 0.4028033935133733, 0.27113581638685375, 0.6821982294917847, 0.15396294813597486, 0.1681592840420707, 0.45226626807646314, 0.31194866264139787, 0.28310861421537564, 0.5038196165719534, 0.27482990107421307, 0.552722929221822, 0.3186278206299983, 0.4080685888817345, 0.8808993159601264, 0.31130135187089025, 0.6292925790788039, 0.1681592840420707, 0.5842010425092414, 1.0395519582258672, 0.7822829209427272, 0.44286293862839293, 0.4007355098588657, 0.5907344060431261, 0.15396294813597486, 0.5131972902026394, 0.6919525937765365, 0.6776595577363246, 0.5635251671342661, 0.7228309027813942, 0.492153274564985, 0.2054056153960229, 0.48320373994231663, 0.6067334015804354, 0.3990529266417713, 0.3079847027651264, 0.6667494644952773, 0.7935196616129864, 0.5317760131861691, 0.39978018390298664, 0.380624135139744, 0.5501266672137395, 0.893048466540184, 0.43754870424409603, 0.22100635228718815, 0.3990529266417713, 0.3079847027651264, 0.6292925790788039, 0.44286293862839293, 0.2685535277782793, 0.4614139617162376, 0.6586695960953836, 0.3193337488194099, 0.5759003002878217, 0.5936779857064671, 0.8327260951481932, 0.47051765530529077, 0.4028033935133733, 0.5675386533058241, 0.5841626468713103, 0.44239736437413096, 0.5832478389416661, 0.5852170493362207, 0.6591479686083069, 0.4041870502123613, 0.3969167865131511, 0.7940886799619793, 0.40493458411320304, 0.29743565697345536, 0.4080685888817345, 0.5852170493362207, 0.5450734127372753, 0.9057013543040535, 0.8487402763352031, 0.5962778729040832, 0.6735131064013661, 0.8067932582325368, 1.2877309806501558, 0.29743565697345536, 0.27113581638685375, 0.2054056153960229, 0.40493458411320304, 0.7936922471899627, 1.0953188382266814, 1.5167986909305917, 0.9436659265695199, 0.7999008890639264, 0.8541663627373048, 0.31130135187089025, 1.325946876607304, 0.9852654021630202, 0.4041870502123613, 0.3969167865131511, 0.31194866264139787, 0.48320373994231663, 0.8299139643496611, 0.44870748689320183, 0.5553523349439898, 0.5962778729040832, 0.5131972902026394, 0.6919525937765365, 0.27482990107421307, 0.28310861421537564, 0.5038196165719534, 0.39978018390298664, 0.380624135139744, 0.6591479686083069, 0.9036854267630731, 0.5450734127372753, 0.9693182626140997, 0.5416314950892109, 0.4505390069746107, 1.1838461753493263, 0.5497303843464859, 0.5416314950892109, 0.4505390069746107, 0.4508619858407013, 0.7731081884902435},
                new double[]{0.8106443110125229, 0.36123420440644344, 0.7520456848935667, 0.42833773532309266, 0.6871984848605489, 0.39204771374620745, 0.4028979513385712, 0.6541161237044746, 0.6155373168271888, 1.026609889502267, 0.8963078212971005, 0.6433281384283139, 0.5002237059826393, 0.8790165555463835, 0.4261574333144366, 0.7754136561837666, 0.9114091776576775, 0.4854349239569046, 0.4449744057351269, 1.0115056152957744, 1.659933435091076, 1.1947931467458364, 0.9333866174608633, 0.6433281384283139, 1.006827963280922, 1.0845268274947542, 0.8963078212971005, 0.4809499360572959, 0.5679781945824924, 1.0880723091374522, 1.265081184526383, 0.7329417256555751, 0.9600689711707685, 0.7425074741562939, 0.567531047582352, 0.515960623365906, 0.4493839590152485, 0.6625193162975406, 0.8478910670085921, 0.8906373784087319, 0.40957266604765424, 0.5353500319132934, 0.9759311981862917, 0.9670631085743358, 1.0880723091374522, 0.44627255649318603, 0.9183140029593616, 0.9558559329526902, 1.0321913309152035, 0.36123420440644344, 1.018944136007164, 0.40957266604765424, 0.5353500319132934, 0.5877069741794665, 0.4809499360572959, 0.42833773532309266, 0.6871984848605489, 0.39204771374620745, 0.5617691979076742, 0.6189084378964191, 0.9881871215933996, 0.7642636481956423, 0.4028979513385712, 0.6699514870145524, 0.582988609161628, 0.567531047582352, 0.6307890170937095, 0.6014757900601442, 0.9640127006187387, 1.0265294900568416, 0.4842839861959971, 0.8862122062229096, 0.894706312727349, 0.4854349239569046, 0.4449744057351269, 0.6014757900601442, 0.8906373784087319, 1.1203036986048183, 1.0824155404196334, 0.9550993928935435, 1.1115275279798638, 1.039351045869597, 1.5186274137076694, 1.1023607245964013, 0.515960623365906, 0.4493839590152485, 0.611473469822067, 0.7540834574554799, 0.992079864875988, 1.7261239601069096, 1.6098852660752319, 1.13652898187979, 0.992079864875988, 1.5673854937119218, 1.3208010566697452, 1.0321913309152035, 1.0265294900568416, 0.4842839861959971, 0.5002237059826393, 0.7157304302960894, 1.0115056152957744, 1.052020993230219, 0.53410772725371, 0.7157304302960894, 1.0170296626796977, 0.7329417256555751, 0.9600689711707685, 0.6763641538065726, 0.4261574333144366, 1.4078813621887736, 0.44627255649318603, 0.9640127006187387, 1.0090505359501154, 1.2769326813243422, 0.8936059628907048, 1.1356406334894906, 0.6085124794227836, 1.1811469738107099, 0.9670631085743358, 0.6189084378964191, 0.6085124794227836, 1.1018043642024244, 0.9759311981862917}
        }
        var indexes = new int[][]{
                new int[]{41, 97, 50, 51, 56, 57, 58, 59, 19, 86, 30, 105, 24, 99, 100, 109, 79, 38, 84, 8, 76, 94, 95, 31, 12, 91, 80, 42, 82, 124, 10, 23, 106, 16, 104, 88, 120, 86, 17, 20, 47, 0, 54, 114, 7, 110, 93, 40, 83, 89, 2, 3, 0, 95, 42, 51, 4, 19, 117, 7, 85, 104, 6, 7, 75, 92, 74, 60, 76, 77, 97, 98, 99, 92, 66, 64, 20, 114, 115, 16, 26, 105, 28, 48, 18, 75, 37, 64, 86, 49, 94, 25, 65, 46, 21, 22, 6, 1, 24, 13, 14, 39, 71, 72, 34, 11, 32, 79, 38, 15, 45, 118, 40, 70, 3, 78, 120, 65, 111, 44, 110, 61, 23, 51, 29},
                new int[]{41, 49, 50, 55, 56, 57, 58, 63, 85, 86, 30, 23, 98, 99, 108, 109, 107, 87, 74, 75, 101, 94, 53, 11, 12, 48, 76, 54, 55, 13, 10, 105, 106, 103, 108, 74, 85, 86, 100, 122, 51, 52, 2, 123, 7, 110, 111, 114, 0, 1, 2, 40, 41, 22, 27, 3, 4, 5, 6, 119, 120, 108, 6, 7, 108, 109, 84, 75, 76, 112, 97, 98, 99, 87, 84, 19, 68, 114, 102, 33, 104, 11, 52, 106, 74, 8, 37, 73, 67, 93, 94, 13, 104, 31, 21, 25, 69, 70, 71, 13, 38, 31, 71, 99, 80, 31, 32, 16, 14, 15, 45, 46, 69, 97, 77, 78, 120, 121, 43, 44, 116, 117, 11, 98},
                new int[]{52, 49, 54, 55, 56, 57, 62, 84, 85, 37, 26, 23, 98, 56, 108, 65, 72, 73, 74, 100, 101, 48, 53, 11, 47, 95, 10, 54, 55, 44, 104, 105, 106, 107, 65, 84, 85, 74, 75, 76, 51, 52, 122, 118, 29, 110, 23, 40, 95, 1, 46, 40, 41, 49, 27, 3, 4, 5, 6, 119, 64, 34, 6, 7, 14, 34, 74, 75, 111, 96, 97, 98, 33, 17, 18, 67, 39, 114, 102, 103, 10, 23, 55, 72, 35, 36, 65, 66, 92, 93, 71, 99, 88, 23, 21, 48, 69, 70, 12, 103, 19, 31, 98, 99, 100, 31, 32, 63, 14, 44, 45, 68, 1, 70, 3, 4, 120, 61, 43, 59, 116, 38, 42}
        }
        when:
        var windows = Util.createRange(4, 6, 1);
        var result = SKIMP.of(timeSeries, windows, false)
        var dst = result.profile()
        var ids = result.indexes()

        then:
        dst.length == 3
        ids.length == 3
        dst[0].length == timeSeries.length - 3
        dst[1].length == timeSeries.length - 4
        dst[2].length == timeSeries.length - 5
        ids[0].length == timeSeries.length - 3
        ids[1].length == timeSeries.length - 4
        ids[2].length == timeSeries.length - 5
        for (int i = 0; i < dst.length; i++) {
            equals(dst[i], distances[i])
            equals(ids[i], indexes[i])
        }
    }

    def 'test skimp cross correlation'() {
        given:
        var distances = new double[][]{
                new double[]{0.9962955398362885, 0.9993301003871092, 0.9934614026789175, 0.997811371919774, 0.9963850360522168, 0.9732252435036992, 0.9619752849821449, 0.9837673428797005, 0.9950185544881165, 0.9898612132431418, 0.9940573951827004, 0.9992604484840765, 0.9974571960975566, 0.9900226770943897, 0.9937729760879688, 0.9849486861552922, 0.9993735798487533, 0.9991936598321207, 0.9988923886189622, 0.9950185544881165, 0.9865535770808921, 0.9913945744087805, 0.9962219900756758, 0.9997786912788559, 0.9974571960975566, 0.98830013889997, 0.9597609390203655, 0.984780507185753, 0.9851253569486363, 0.9875634214201388, 0.9940573951827004, 0.9997786912788559, 0.9729897673412241, 0.9851274472659416, 0.9966654261834126, 0.9527758343873527, 0.9896328069915741, 0.9946719950752185, 0.9991936598321207, 0.9761859695069176, 0.9982391442709923, 0.9962955398362885, 0.9959797316462236, 0.9754275834523408, 0.9501334144358071, 0.9990562478920397, 0.9971056053664762, 0.9982391442709923, 0.9685253924520266, 0.9778721060318551, 0.9934614026789175, 0.997811371919774, 0.9898490753944822, 0.8923357217122144, 0.9959797316462236, 0.9961638451916183, 0.9963850360522168, 0.9876586857733922, 0.9735969516896961, 0.9837673428797005, 0.9864658710662406, 0.9962760582511387, 0.9160261647219433, 0.971421974052641, 0.9988991883723063, 0.9961262285675797, 0.9930814598566209, 0.9841937985961045, 0.9550241644647138, 0.9654735517527386, 0.9895234100038365, 0.9830128317589462, 0.9819639243320343, 0.9863854284408877, 0.9930814598566209, 0.9988991883723063, 0.9865535770808921, 0.9787152440705936, 0.9763515883337924, 0.9993735798487533, 0.9597609390203655, 0.9582669559803478, 0.9851253569486363, 0.9685253924520266, 0.9988923886189622, 0.9973950046666974, 0.9946719950752185, 0.9960616762851098, 0.9909947725303457, 0.9778721060318551, 0.8849558077646458, 0.98830013889997, 0.9961262285675797, 0.9971056053664762, 0.9913945744087805, 0.9962219900756758, 0.9091409615661378, 0.9993301003871092, 0.9961525613591943, 0.9900226770943897, 0.9937729760879688, 0.9630153402080959, 0.982240866384821, 0.9778948203728365, 0.9966654261834126, 0.9992604484840765, 0.9729897673412241, 0.9941162993703119, 0.9976166273313181, 0.9849486861552922, 0.9990562478920397, 0.9975837973158355, 0.997096777400554, 0.9782256484609889, 0.994691042793896, 0.9763515883337924, 0.964095774160478, 0.9838177139350067, 0.9975837973158355, 0.9438730903621703, 0.9988570799558026, 0.9797294631927257, 0.9982886107814037, 0.9940420550983964, 0.9875634214201388},
                new double[]{0.9879282191388751, 0.9808551131414313, 0.9951156192248711, 0.9927879002717841, 0.9787097155933326, 0.9566154363179544, 0.9898025956864942, 0.983774942617411, 0.9926485369072234, 0.9534605575678274, 0.9976295410601279, 0.9971722455190458, 0.9795455222760189, 0.9902688031876243, 0.991984951255705, 0.974616579395729, 0.9924468525475538, 0.9694497363512449, 0.9898476311920578, 0.983348002676807, 0.9224016395140981, 0.9903091468323356, 0.9603990849916347, 0.9971722455190458, 0.9658709141931116, 0.8919331726148765, 0.9388033431601315, 0.9803872417589424, 0.9839411051138155, 0.9651032861516875, 0.9976295410601279, 0.9736628541328668, 0.9521201607965923, 0.9540777523808609, 0.9682439386006297, 0.9477515485984235, 0.9757785154334963, 0.9957808533163781, 0.9766514145705758, 0.9631874579406634, 0.9840756761738637, 0.9905145422862677, 0.9555445151595261, 0.9370326546633612, 0.9717214271799823, 0.9840175804558494, 0.9855125267749122, 0.9697360650020304, 0.9202464436410226, 0.9808551131414313, 0.9951156192248711, 0.9840756761738637, 0.9905145422862677, 0.9603990849916347, 0.9803872417589424, 0.9927879002717841, 0.9787097155933326, 0.9566154363179544, 0.9898025956864942, 0.9668338844128397, 0.9647546449287512, 0.9306567250459242, 0.9778613136046012, 0.983774942617411, 0.9677899877003812, 0.9658754002000305, 0.9804284571994822, 0.9659821958369876, 0.9657521005166207, 0.9565523955479542, 0.983663282844063, 0.9842457064584074, 0.9369423168356241, 0.9836027982589067, 0.9911532029960769, 0.983348002676807, 0.9657521005166207, 0.970289497472694, 0.9179705056811803, 0.9279639943326443, 0.9644452698284982, 0.9546380095505582, 0.9349084638470527, 0.8341748921473788, 0.9911532029960769, 0.9926485369072234, 0.9957808533163781, 0.9836027982589067, 0.9370052616750547, 0.8800276642625753, 0.7699321731191243, 0.9109494619031689, 0.936015856767474, 0.9270399824768123, 0.9903091468323356, 0.8241864880415335, 0.9029252087300542, 0.983663282844063, 0.9842457064584074, 0.9902688031876243, 0.9766514145705758, 0.9311242811777429, 0.9798661591205987, 0.9691583784072259, 0.9644452698284982, 0.9736628541328668, 0.9521201607965923, 0.9924468525475538, 0.991984951255705, 0.974616579395729, 0.9840175804558494, 0.9855125267749122, 0.9565523955479542, 0.9183352649456042, 0.970289497472694, 0.9060422105762783, 0.9706635323527426, 0.9797014603194332, 0.8598508233110772, 0.9697796504526265, 0.9706635323527426, 0.9797014603194332, 0.9796723469723779, 0.9402303728889334},
                new double[]{0.945237983418586, 0.989125820797237, 0.9528689406527472, 0.984710565374857, 0.9606465202004472, 0.9871915491788643, 0.9864727700672652, 0.964344341392486, 0.9684261509660987, 0.9121726778980119, 0.9330526907901371, 0.9655107421921967, 0.9791480203310828, 0.9356108245896143, 0.9848658201692376, 0.949894471816977, 0.9307777759067797, 0.9803627445502462, 0.9834998148533892, 0.9147380325187597, 0.770385082588895, 0.8810391113740985, 0.9273991185287473, 0.9655107421921967, 0.9155247876962992, 0.901983463370347, 0.9330526907901371, 0.9807239299172069, 0.9731167308732344, 0.9013415541740244, 0.8666307997131103, 0.9552330355660856, 0.9231889642162585, 0.9540568875685034, 0.9731590425025065, 0.9778153862613221, 0.9831711714483151, 0.9634223462943866, 0.9400900615405859, 0.9338970883484351, 0.9860208526022181, 0.9761166952775363, 0.9206298580338891, 0.9220657453362119, 0.9013415541740244, 0.9834034004434197, 0.9297249493307295, 0.9238616196199285, 0.9112150880319584, 0.989125820797237, 0.9134794039747178, 0.9860208526022181, 0.9761166952775363, 0.971216709375068, 0.9807239299172069, 0.984710565374857, 0.9606465202004472, 0.9871915491788643, 0.9737012806901807, 0.9680793621250512, 0.918623851059746, 0.9513250896705573, 0.9864727700672652, 0.9625970837539158, 0.9716770234656492, 0.9731590425025065, 0.9668421013261627, 0.9698522394976271, 0.9225566260871472, 0.9121864338369701, 0.9804557517261763, 0.9345523271284603, 0.9332917178304859, 0.9803627445502462, 0.9834998148533892, 0.9698522394976271, 0.9338970883484351, 0.8954099685743637, 0.9023647164881727, 0.9239820958078654, 0.8970422128785811, 0.9099791169541479, 0.807814231527963, 0.8987334027389414, 0.9778153862613221, 0.9831711714483151, 0.9688416829753135, 0.9526131782659991, 0.9179814618089701, 0.7517080061954033, 0.7840224525061567, 0.8923584894456074, 0.9179814618089701, 0.7952752261751196, 0.8546237140583404, 0.9112150880319584, 0.9121864338369701, 0.9804557517261763, 0.9791480203310828, 0.9573108292623479, 0.9147380325187597, 0.9077709858169086, 0.976227411307323, 0.9573108292623479, 0.9138042221024684, 0.9552330355660856, 0.9231889642162585, 0.9618776276204599, 0.9848658201692376, 0.834822505833457, 0.9834034004434197, 0.9225566260871472, 0.9151514179915654, 0.8641202439471521, 0.9334556985905147, 0.8925266959639657, 0.969142713532228, 0.8837409855214835, 0.9220657453362119, 0.9680793621250512, 0.969142713532228, 0.8988355952520409, 0.9206298580338891}
        }
        var indexes = new int[][]{
                new int[]{41, 97, 50, 51, 56, 57, 58, 59, 19, 86, 30, 105, 24, 99, 100, 109, 79, 38, 84, 8, 76, 94, 95, 31, 12, 91, 80, 42, 82, 124, 10, 23, 106, 16, 104, 88, 120, 86, 17, 20, 47, 0, 54, 114, 7, 110, 93, 40, 83, 89, 2, 3, 0, 95, 42, 51, 4, 19, 117, 7, 85, 104, 6, 7, 75, 92, 74, 60, 76, 77, 97, 98, 99, 92, 66, 64, 20, 114, 115, 16, 26, 105, 28, 48, 18, 75, 37, 64, 86, 49, 94, 25, 65, 46, 21, 22, 6, 1, 24, 13, 14, 39, 71, 72, 34, 11, 32, 79, 38, 15, 45, 118, 40, 70, 3, 78, 120, 65, 111, 44, 110, 61, 23, 51, 29},
                new int[]{41, 49, 50, 55, 56, 57, 58, 63, 85, 86, 30, 23, 98, 99, 108, 109, 107, 87, 74, 75, 101, 94, 53, 11, 12, 48, 76, 54, 55, 13, 10, 105, 106, 103, 108, 74, 85, 86, 100, 122, 51, 52, 2, 123, 7, 110, 111, 114, 0, 1, 2, 40, 41, 22, 27, 3, 4, 5, 6, 119, 120, 108, 6, 7, 108, 109, 84, 75, 76, 112, 97, 98, 99, 87, 84, 19, 68, 114, 102, 33, 104, 11, 52, 106, 74, 8, 37, 73, 67, 93, 94, 13, 104, 31, 21, 25, 69, 70, 71, 13, 38, 31, 71, 99, 80, 31, 32, 16, 14, 15, 45, 46, 69, 97, 77, 78, 120, 121, 43, 44, 116, 117, 11, 98},
                new int[]{52, 49, 54, 55, 56, 57, 62, 84, 85, 37, 26, 23, 98, 56, 108, 65, 72, 73, 74, 100, 101, 48, 53, 11, 47, 95, 10, 54, 55, 44, 104, 105, 106, 107, 65, 84, 85, 74, 75, 76, 51, 52, 122, 118, 29, 110, 23, 40, 95, 1, 46, 40, 41, 49, 27, 3, 4, 5, 6, 119, 64, 34, 6, 7, 14, 34, 74, 75, 111, 96, 97, 98, 33, 17, 18, 67, 39, 114, 102, 103, 10, 23, 55, 72, 35, 36, 65, 66, 92, 93, 71, 99, 88, 23, 21, 48, 69, 70, 12, 103, 19, 31, 98, 99, 100, 31, 32, 63, 14, 44, 45, 68, 1, 70, 3, 4, 120, 61, 43, 59, 116, 38, 42}
        }
        when:
        var windows = Util.createRange(4, 6, 1);
        var result = SKIMP.of(timeSeries, windows, true)
        var dst = result.profile()
        var ids = result.indexes()

        then:
        dst.length == 3
        ids.length == 3
        dst[0].length == timeSeries.length - 3
        dst[1].length == timeSeries.length - 4
        dst[2].length == timeSeries.length - 5
        ids[0].length == timeSeries.length - 3
        ids[1].length == timeSeries.length - 4
        ids[2].length == timeSeries.length - 5
        for (int i = 0; i < dst.length; i++) {
            equals(dst[i], distances[i])
            equals(ids[i], indexes[i])
        }
    }
}
