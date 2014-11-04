package tardis.common.blocks;

import tardis.common.tileents.TardisEngineTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TardisEngineBlock extends TardisAbstractBlockContainer
{

	public TardisEngineBlock(int par1)
	{
		super(par1);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TardisEngineTileEntity();
	}

	@Override
	public void initData()
	{
		setUnlocalizedName("TardisEngineBlock");
		setLightValue(0.8F);
	}

	@Override
	public void initRecipes()
	{
		// TODO Auto-generated method stub

	}
	
	@Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer pl, int s, float i, float j, float k)
    {
		boolean superEffect = super.onBlockActivated(w, x, y, z, pl, s, i, j, k);
		TileEntity te = w.getBlockTileEntity(x,y,z);
		if(te instanceof TardisEngineTileEntity)
		{
			superEffect = ((TardisEngineTileEntity)te).activate(pl, s, y, i, j, k);;
		}
		return superEffect;
    }
}