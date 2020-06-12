package rs.ac.uns.ftn.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ratings")
public class Rating {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "frequency1")
    private Integer frequency1;
	
	@Column(name = "frequency2")
    private Integer frequency2;
	
	@Column(name = "frequency3")
    private Integer frequency3;
	
	@Column(name = "frequency4")
    private Integer frequency4;
	
	@Column(name = "frequency5")
    private Integer frequency5;
	
	@Column(name = "frequency_like")
    private Integer frequencyLike;
	
	@Column(name = "frequency_dislike")
    private Integer frequencyDislike;
	
	@Column(name = "average_grade")
    private Double averageGrade;
	
	@Column(name = "like_dislike_ratio")
    private Double likeDislikeRatio;

	public Rating() {
		this.frequency1 = 0;
		this.frequency2 = 0;
		this.frequency3 = 0;
		this.frequency4 = 0;
		this.frequency5 = 0;
		this.frequencyLike = 0;
		this.frequencyDislike = 0;
		
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFrequency1() {
		return frequency1;
	}

	public void setFrequency1(Integer frequency1) {
		this.frequency1 = frequency1;
	}

	public Integer getFrequency2() {
		return frequency2;
	}

	public void setFrequency2(Integer frequency2) {
		this.frequency2 = frequency2;
	}

	public Integer getFrequency3() {
		return frequency3;
	}

	public void setFrequency3(Integer frequency3) {
		this.frequency3 = frequency3;
	}

	public Integer getFrequency4() {
		return frequency4;
	}

	public void setFrequency4(Integer frequency4) {
		this.frequency4 = frequency4;
	}

	public Integer getFrequency5() {
		return frequency5;
	}

	public void setFrequency5(Integer frequency5) {
		this.frequency5 = frequency5;
	}

	public Integer getFrequencyLike() {
		return frequencyLike;
	}

	public void setFrequencyLike(Integer frequencyLike) {
		this.frequencyLike = frequencyLike;
	}

	public Integer getFrequencyDislike() {
		return frequencyDislike;
	}

	public void setFrequencyDislike(Integer frequencyDislike) {
		this.frequencyDislike = frequencyDislike;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public Double getLikeDislikeRatio() {
		return likeDislikeRatio;
	}

	public void setLikeDislikeRatio(Double likeDislikeRatio) {
		this.likeDislikeRatio = likeDislikeRatio;
	}

	public Rating(Long id, Integer frequency1, Integer frequency2, Integer frequency3, Integer frequency4,
			Integer frequency5, Integer frequencyLike, Integer frequencyDislike, Double averageGrade,
			Double likeDislikeRatio) {
		super();
		this.id = id;
		this.frequency1 = frequency1;
		this.frequency2 = frequency2;
		this.frequency3 = frequency3;
		this.frequency4 = frequency4;
		this.frequency5 = frequency5;
		this.frequencyLike = frequencyLike;
		this.frequencyDislike = frequencyDislike;
		this.averageGrade = averageGrade;
		this.likeDislikeRatio = likeDislikeRatio;
	}
	
	
	
	

	
	

	
	


}
