package com.weisen.www.code.yjf.basic.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.weisen.www.code.yjf.basic.domain.Files} entity.
 */
@SuppressWarnings("serial")
public class FilesDTO implements Serializable {

    private Long id;

    private String userid;

    private String name;
    
    private String url;
    
    private String uuid;
    
    private Integer height;
    
    private Integer width;

    private Integer size;

    private String file;

    private String fileContentType;

    private String createDate;
    
    private String originname;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilesDTO filesDTO = (FilesDTO) o;
        if (filesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilesDTO{" +
            "id=" + getId() +
            ", userid='" + getUserid() + "'" +
            ", name='" + getName() + "'" +
            ", size=" + getSize() +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            "}";
    }
}
